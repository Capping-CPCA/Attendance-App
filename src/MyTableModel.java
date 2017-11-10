/**
 * PEP Capping 2017 Algozzine's Class
 *
 * This class is what allows us to create our own table model for our Upload_Frame JTable
 * This table model only allows certain cells to be editable, along with having drop downs within cells
 * This code is open-source: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 *
 * @author Sami Ellougani, Carlie Maxwell
 * @copyright 2017 Marist College
 * @version 0.1.0
 * @since 0.1.0
 */

package javaApplication;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	
    public MyTableModel() {
      super(new String[]{
				"First", "Last", "Date", "Curriculum", "Topic", "Day", "Time", 
				"Location", "Language","Sex", "Race", "Age","New","18&Under", "Zipcode", "Instructor"
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

    //Set which columns are editable
    @Override
    public boolean isCellEditable(int row, int column) {
      return column == 12 || column == 9 || column == 10 || column == 11 || column == 13 || column == 14;
    }

    //Sets which columns can change values, and how they are displayed
    @Override
    public void setValueAt(Object aValue, int row, int column) {
      if (aValue instanceof Boolean && column == 12) {
        Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(12, (boolean)aValue);
        fireTableCellUpdated(row, column);
      } else if(column == 9){
    	  Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(9, (String)aValue);
        fireTableCellUpdated(row, column);
      } else if(column == 10){
      	Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(10, (String)aValue);
        fireTableCellUpdated(row, column);
      } else if(column == 11){
      	Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(11, (String)aValue);
        fireTableCellUpdated(row, column);
      } else if(column == 13){
    		Vector rowData = (Vector)getDataVector().get(row);
    		rowData.set(13, (String)aValue);
    		fireTableCellUpdated(row, column);
      } else if(column == 14){
    		Vector rowData = (Vector)getDataVector().get(row);
    		rowData.set(14, (String)aValue);
    		fireTableCellUpdated(row, column);
      } else if(column == 2){
    		Vector rowData = (Vector)getDataVector().get(row);
    		rowData.set(2, (String)aValue);
    		fireTableCellUpdated(row, column);
      } else if(column == 5){
        Vector rowData = (Vector)getDataVector().get(row);
    	  rowData.set(5, (String)aValue);
    	  fireTableCellUpdated(row, column);
      } else if(column == 3){
        Vector rowData = (Vector)getDataVector().get(row);
    	  rowData.set(3, (String)aValue);
    	  fireTableCellUpdated(row, column);
      } else if(column == 4){
        Vector rowData = (Vector)getDataVector().get(row);
    	  rowData.set(4, (String)aValue);
    	  fireTableCellUpdated(row, column);
      } else if(column == 6){
        Vector rowData = (Vector)getDataVector().get(row);
    	  rowData.set(6, (String)aValue);
    	  fireTableCellUpdated(row, column);
      } else if(column == 7){
        Vector rowData = (Vector)getDataVector().get(row);
    	  rowData.set(7, (String)aValue);
    	  fireTableCellUpdated(row, column);
      } else if(column == 8){
        Vector rowData = (Vector)getDataVector().get(row);
    	  rowData.set(8, (String)aValue);
    	  fireTableCellUpdated(row, column);
      } 
    }
}