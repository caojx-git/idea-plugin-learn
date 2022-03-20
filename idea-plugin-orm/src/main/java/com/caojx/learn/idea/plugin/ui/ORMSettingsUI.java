package com.caojx.learn.idea.plugin.ui;

import com.caojx.learn.idea.plugin.domain.model.vo.CodeGenContextVO;
import com.caojx.learn.idea.plugin.domain.model.vo.ORMConfigVO;
import com.caojx.learn.idea.plugin.domain.service.IProjectGenerator;
import com.caojx.learn.idea.plugin.infrastructure.data.PersistentStateService;
import com.caojx.learn.idea.plugin.infrastructure.po.Table;
import com.caojx.learn.idea.plugin.infrastructure.utils.DBHelper;
import com.caojx.learn.idea.plugin.module.FileChooserComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ORMSettingsUI implements Configurable {
    private JPanel mainPanel;
    private JTextField classpath;
    private JTextField projectName;
    private JTextField poPath;
    private JButton poButton;
    private JTextField daoPath;
    private JTextField xmlPath;
    private JButton daoButton;
    private JButton xmlButton;
    private JTextField host;
    private JTextField port;
    private JTextField database;
    private JTextField user;
    private JPasswordField password;
    private JButton selectButton;
    private JButton testButton;
    private JTable table;


    private ORMConfigVO ormConfigVO;

    private Project project;
    private IProjectGenerator projectGenerator;


    public ORMSettingsUI(Project project, IProjectGenerator projectGenerator) {
        this.project = project;
        this.projectGenerator = projectGenerator;

        // 读取持久化的数据
        ormConfigVO = PersistentStateService.getInstance(null != project ? project : ProjectManager.getInstance().getDefaultProject()).getORMConfig();
        ormConfigVO.getTableNames().clear();
        this.projectName.setText(project.getName());
        this.classpath.setText(project.getBasePath());


        this.database.setText(ormConfigVO.getDatabase());
        this.host.setText(ormConfigVO.getHost());
        this.port.setText(ormConfigVO.getPort());
        this.poPath.setText(ormConfigVO.getPoPath());
        this.daoPath.setText(ormConfigVO.getDaoPath());
        this.xmlPath.setText(ormConfigVO.getXmlPath());
        this.user.setText(ormConfigVO.getUser());
        this.password.setText(ormConfigVO.getPassword());

        // 选择PO生成目录
        poButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChooserComponent component = FileChooserComponent.getInstance(project);
                VirtualFile baseDir = project.getBaseDir();
                VirtualFile virtualFile = component.showFolderSelectionDialog("选择PO生成目录", baseDir, baseDir);
                if (virtualFile != null) {
                    ORMSettingsUI.this.poPath.setText(virtualFile.getPath());
                }
            }
        });


        // 选择DAO生成目录
        daoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChooserComponent component = FileChooserComponent.getInstance(project);
                VirtualFile baseDir = project.getBaseDir();
                VirtualFile virtualFile = component.showFolderSelectionDialog("选择DAO生成目录", baseDir, baseDir);
                if (virtualFile != null) {
                    ORMSettingsUI.this.daoPath.setText(virtualFile.getPath());
                }
            }
        });

        // 选择XMl生成目录
        xmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChooserComponent component = FileChooserComponent.getInstance(project);
                VirtualFile baseDir = project.getBaseDir();
                VirtualFile virtualFile = component.showFolderSelectionDialog("选择XML生成目录", baseDir, baseDir);
                if (virtualFile != null) {
                    ORMSettingsUI.this.xmlPath.setText(virtualFile.getPath());
                }
            }
        });

        // 查询表名
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DBHelper dbHelper = new DBHelper(host.getText(), Integer.valueOf(port.getText()), user.getText(), password.getText(), database.getToolTipText());
                    List<String> tableList = dbHelper.getAllTableName(database.getText());
                    String[] title = {"", "表名"};
                    Object[][] data = new Object[tableList.size()][2];
                    for (int i = 0; i < tableList.size(); i++) {
                        data[i][1] = tableList.get(i);
                    }
                    table.setModel(new DefaultTableModel(data, title));

                    // 设置列为单选框
                    TableColumn tableColumn = table.getColumnModel().getColumn(0);
                    tableColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
                    tableColumn.setCellEditor(table.getDefaultEditor(Boolean.class));
                    tableColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));
                    tableColumn.setMaxWidth(100);
                } catch (Exception ex) {
                    Messages.showWarningDialog(project, "数据库连接错误,请检查配置.", "Warning");
                }
            }
        });

        // 表添加事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int rowIdx = table.rowAtPoint(e.getPoint());
                    Boolean flag = (Boolean) table.getValueAt(rowIdx, 0);
                    Set<String> tableNames = ormConfigVO.getTableNames();
                    if (Objects.nonNull(flag) && flag) {
                        tableNames.add(table.getValueAt(rowIdx, 1).toString());
                    } else {
                        tableNames.remove(table.getValueAt(rowIdx, 1).toString());
                    }
                }
            }
        });

        // 测试数据库连接
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DBHelper dbHelper = new DBHelper(host.getText(), Integer.valueOf(port.getText()), user.getText(), password.getText(), database.getText());
                    String mysqlVersion = dbHelper.testDatabase();
                    Messages.showInfoMessage(project, "Connection successful! \r\nMySQL version : " + mysqlVersion, "OK");
                } catch (Exception exception) {
                    Messages.showWarningDialog(project, "数据库连接错误,请检查配置.", "Warning");
                }
            }
        });

    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Config";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        // 获取配置
        ormConfigVO.setUser(this.user.getText());
        ormConfigVO.setPassword(new String(this.password.getPassword()));
        ormConfigVO.setProjectName(this.projectName.getText());
        ormConfigVO.setClasspath(this.classpath.getText());
        ormConfigVO.setDatabase(this.database.getText());
        ormConfigVO.setHost(this.host.getText());
        ormConfigVO.setPort(this.port.getText() != null ? this.port.getText() : "3306");
        ormConfigVO.setPoPath(this.poPath.getText());
        ormConfigVO.setDaoPath(this.daoPath.getText());
        ormConfigVO.setXmlPath(this.xmlPath.getText());

        // 连接
        DBHelper dbHelper = new DBHelper(ormConfigVO.getHost(), Integer.parseInt(ormConfigVO.getPort()), ormConfigVO.getUser(), ormConfigVO.getPassword(), ormConfigVO.getDatabase());

        // 组装代码生产上下文
        CodeGenContextVO codeGenContext = new CodeGenContextVO();
        codeGenContext.setModelPackage(ormConfigVO.getPoPath() + "/po/");
        codeGenContext.setDaoPackage(ormConfigVO.getDaoPath() + "/dao/");
        codeGenContext.setMapperDir(ormConfigVO.getXmlPath() + "/mapper/");
        Set<String> tableNames = ormConfigVO.getTableNames();
        List<Table> tables = new ArrayList<>();
        for (String tableName : tableNames) {
            tables.add(dbHelper.getTable(tableName));
        }
        codeGenContext.setTables(tables);

        // 生成代码
        projectGenerator.generation(project, codeGenContext);

    }
}
