package com.caojx.learn.idea.plugin.markbook.data;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * 笔记存放容器
 */
public class DataCenter {

    /**
     * 选择的文本
     */
    public static String SELECTED_TEXT = null;

    /**
     * 当前的文件名称
     */
    public static String CURRENT_FILE_NAME = null;

    /**
     *  当前的文件类型
     */
    public static String CURRENT_FILE_TYPE = null;

    /**
     * 笔记列表集合
     */
    public static List<OneNoteData> NOTE_LIST = new LinkedList<>();


    /**
     * 表列明
     */
    public static String[] COLUMN_NAME={"标题","备注","文件名","代码段"};

    /**
     * 表model
     */
    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, COLUMN_NAME);


    /**
     * 清空列表
     */
    public static void reset(){
        NOTE_LIST.clear();
        TABLE_MODEL.setDataVector(null, COLUMN_NAME);
    }
}
