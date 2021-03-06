package com.caojx.learn.idea.plugin.ui;



import com.caojx.learn.idea.plugin.domain.model.vo.Data;
import com.caojx.learn.idea.plugin.domain.model.vo.GoPicture;
import com.caojx.learn.idea.plugin.domain.service.IStock;
import com.caojx.learn.idea.plugin.domain.service.impl.IStockImpl;
import com.caojx.learn.idea.plugin.infrastructure.PersistentDataService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ConsoleUI {

    private JTabbedPane tabbedPane1;
    private JPanel one;
    private JPanel two;
    private JLabel picMin;
    private JTable table;
    private JLabel picDay;

    // 查询数据服务
    private IStock stock = new IStockImpl();

    // 表格列
    private DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][]{}, new String[]{"股票", "代码", "最新", "涨跌", "涨幅"});

    public ConsoleUI() {
        // 初始数据
        table.setModel(defaultTableModel);
        addRows(PersistentDataService.getInstance().getGids());

        // 添加事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 选择表格中的一行股票数据后，显示k线图片
                int row = table.getSelectedRow();
                Object value = table.getValueAt(row, 1);

                // 获取股票k线图片
                GoPicture goPicture = stock.queryGidGoPicture(value.toString());
                try {
                    // 分钟K线
                    picMin.setSize(545, 300);
                    picMin.setIcon(new ImageIcon(new URL(goPicture.getMinurl())));

                    // 当日K线
                    picDay.setSize(545, 300);
                    picDay.setIcon(new ImageIcon(new URL(goPicture.getDayurl())));
                } catch (MalformedURLException m) {
                    m.printStackTrace();
                }
            }
        });
    }

    public JTabbedPane getPanel() {
        return tabbedPane1;
    }

    public void addRows(List<String> gids) {
        // 查询
        List<Data> dataList = stock.queryPresetStockData(gids);

        // 清空
        int rowCount = defaultTableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            defaultTableModel.removeRow(0);
        }

        // 添加
        for (Data data : dataList) {
            defaultTableModel.addRow(new String[]{data.getName(), data.getGid(), data.getNowPri(), data.getIncrease(), data.getIncrePer()});
            table.setModel(defaultTableModel);
        }
    }

}
