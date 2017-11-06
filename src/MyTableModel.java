package javaApplication;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	
    public MyTableModel() {
      super(new String[]{
				"First", "Last", "Date", "Curriculum", "Topic", "Day", "Time", 
				"Location", "Language","Sex", "Race", "Age","New","18&Under", "Zipcode"
			}, 0);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
      Class type = String.class;
      switch (columnIndex) {
        case 12:
          type = Boolean.class;
          break;
      }
      return type;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
      return column == 12 || column == 9 || column == 10 || column == 11 || column == 13 || column == 14;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
      if (aValue instanceof Boolean && column == 12) {
        System.out.println(aValue);
        Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(12, (boolean)aValue);
        fireTableCellUpdated(row, column);
      } else if(column == 9){
    	System.out.println(aValue);
    	Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(9, (String)aValue);
        fireTableCellUpdated(row, column);
      } else if(column == 10){
    	System.out.println(aValue);
      	Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(10, (String)aValue);
        fireTableCellUpdated(row, column);
      } else if(column == 11){
    	System.out.println(aValue);
      	Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(11, (String)aValue);
        fireTableCellUpdated(row, column);
      } else if(column == 13){
		System.out.println(aValue);
		Vector rowData = (Vector)getDataVector().get(row);
		rowData.set(13, (String)aValue);
		fireTableCellUpdated(row, column);
      } else if(column == 14){
  		System.out.println(aValue);
  		Vector rowData = (Vector)getDataVector().get(row);
  		rowData.set(14, (String)aValue);
  		fireTableCellUpdated(row, column);
      }
    }
}