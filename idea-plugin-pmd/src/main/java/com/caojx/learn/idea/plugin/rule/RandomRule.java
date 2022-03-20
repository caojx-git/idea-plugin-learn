package com.caojx.learn.idea.plugin.rule;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.daemon.impl.actions.AddImportAction;
import com.intellij.codeInspection.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * ref: https://rules.sonarsource.com/java/type/Security%20Hotspot/RSPEC-2245
 */
public class RandomRule extends AbstractBaseJavaLocalInspectionTool {

    /**
     * 通过继承 AbstractBaseJavaLocalInspectionTool Override buildVisitor 方法，扩展检测代码。当你写了这段方法后，IDEA 会把一行行的代码都通过这个方法传进来
     *
     * @param holder
     * @param isOnTheFly
     * @return
     */
    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new JavaElementVisitor() {
            @Override
            public void visitNewExpression(PsiNewExpression expression) {
                if ("java.util.Random".equals(Objects.requireNonNull(expression.getClassReference()).getQualifiedName())) {
                    holder.registerProblem(expression, "Unsafe pseudorandom generator used", ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new ReplacePseudorandomGeneratorQuickFix());
                }
            }
        };
    }

    public static class ReplacePseudorandomGeneratorQuickFix implements LocalQuickFix {

        @Nls(capitalization = Nls.Capitalization.Sentence)
        @NotNull
        @Override
        public String getFamilyName() {
            return "!Fix：replace by SecureRandom";
        }

        @Override
        public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor problemDescriptor) {
            PsiNewExpression newExpression = ObjectUtils.tryCast(problemDescriptor.getPsiElement(), PsiNewExpression.class);
            if (newExpression == null) {
                return;
            }

            PsiElement parentPsiElement = newExpression.getParent();
            PsiTypeElement psiTypeElement = null;
            if (parentPsiElement instanceof PsiAssignmentExpression) {
                // 初始化，parent指向声明点
                PsiAssignmentExpression assignmentExpression = ObjectUtils.tryCast(parentPsiElement, PsiAssignmentExpression.class);
                if (assignmentExpression != null) {
                    PsiReference lRef = assignmentExpression.getLExpression().getReference();
                    if (lRef != null) {
                        parentPsiElement = lRef.resolve();
                    }
                }
            }

            if (parentPsiElement instanceof PsiLocalVariable) {
                // 变量声明同时初始化
                PsiLocalVariable localVariable = ObjectUtils.tryCast(parentPsiElement, PsiLocalVariable.class);
                if (localVariable != null) {
                    psiTypeElement = localVariable.getTypeElement();
                }
            } else if (parentPsiElement instanceof PsiField) {
                // field 变量
                PsiField field = ObjectUtils.tryCast(parentPsiElement, PsiField.class);
                if (field != null) {
                    psiTypeElement = field.getTypeElement();
                }
            }

            if (psiTypeElement == null) {
                return;
            }

            PsiElementFactory factory = JavaPsiFacade.getElementFactory(project);
            psiTypeElement.replace(factory.createTypeElementFromText("SecureRandom", null));
            PsiNewExpression secureNewExp = (PsiNewExpression) factory.createExpressionFromText("new SecureRandom()", null);

            newExpression.replace(secureNewExp);

            // point NewExpression to element in file
            secureNewExp = ObjectUtils.tryCast(((PsiVariable) parentPsiElement).getInitializer(), PsiNewExpression.class);
            if (secureNewExp == null) {
                return;
            }

            // import java.security.SecureRandom
            try {

                PsiFile psiFile = problemDescriptor.getPsiElement().getContainingFile();
                Document document = PsiDocumentManager.getInstance(project).getDocument(psiFile);
                PsiJavaCodeReferenceElement psiJavaCodeReferenceElement = secureNewExp.getClassOrAnonymousClassReference();

                if (document != null && psiJavaCodeReferenceElement != null) {
                    Editor[] editors = EditorFactory.getInstance().getEditors(document, project);
                    if (!FileModificationService.getInstance().prepareFileForWrite(psiFile)) {
                        return;
                    }
                    if (psiJavaCodeReferenceElement.getReferenceName() == null) {
                        return;
                    }

                    ApplicationManager.getApplication().runWriteAction(() -> {
                        PsiClass[] psiClasses = PsiShortNamesCache.getInstance(project).getClassesByName(psiJavaCodeReferenceElement.getReferenceName(), psiJavaCodeReferenceElement.getResolveScope());
                        for (PsiClass psiClass : psiClasses) {
                            if ("java.security.SecureRandom".equals(psiClass.getQualifiedName())) {
                                (new AddImportAction(project, psiJavaCodeReferenceElement, editors[0], new PsiClass[]{psiClass}) {
                                    @Override
                                    protected void bindReference(PsiReference ref, PsiClass targetClass) {
                                        ref.bindToElement(targetClass);
                                    }
                                }).execute();
                            }
                        }
                    });
                }
            } catch (Exception ignore) {

            }

        }
    }
}
