package com.caojx.learn.idea.plugin.markbook.data;

public class DataConvert {

    /**
     * 一条笔记内容，转表的行
     *
     * @param oneNoteData
     * @return
     */
    public static String[] convertTableRow(OneNoteData oneNoteData) {
        String[] row = new String[4];
        row[0] = oneNoteData.getTitle();
        row[1] = oneNoteData.getMark();
        row[2] = oneNoteData.getFileName();
        row[3] = oneNoteData.getSourceCode();
        return row;
    }
}
