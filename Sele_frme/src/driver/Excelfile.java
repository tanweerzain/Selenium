package driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelfile {
    InputStream inp=null;
	Workbook wb = null;
    Sheet sheet = null;
    FormulaEvaluator objFormulaEvaluator = null;
    Iterator<Row> rowIterator =null;

	public static void main(String args[]){
		Excelfile aa = new Excelfile();
		
		aa.openfile();
		aa.selectsheet(0);
		String[] values = aa.getrow();
		while (values != null){
			System.out.println(values[0] + " " + values[1] + " " + values[2] +  " " + values[3]);
			values = aa.getrow();
		}
		aa.fileclose();
	}
	public String openfiledialog(){
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files (*.xls,*.xlsx)", "xls", "xlsx");
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Open an Excel file to execute");
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        SwingUtilities.updateComponentTreeUI(fileChooser);
	    }catch(Exception ex) {
	        ex.printStackTrace();
	    }
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		  file = fileChooser.getSelectedFile();
		}
		else
			System.exit(0);
		return file.getPath();
	}
	public void openfile(){
		String filepath = openfiledialog();
		try {
			inp = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			wb = WorkbookFactory.create(inp);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (FilenameUtils.getExtension(filepath) == "xls")
			this.objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) wb);
		else
			this.objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) wb);

	}
	public void selectsheet(int shtnum){
	    sheet = wb.getSheetAt(shtnum);
	    this.rowIterator = sheet.iterator();
	}
	
	public String[] getrow(){
	    DataFormatter objDefaultFormat = new DataFormatter();
	    String[] content = {"","","",""};
	    if(rowIterator.hasNext()){               
	        Row row = rowIterator.next();               
	        Cell cells = null;
	        for (int i = 0;i<4;i++){
	        	cells = row.getCell(i);
	        	objFormulaEvaluator.evaluate(cells);
            	content[i]= objDefaultFormat.formatCellValue(cells,objFormulaEvaluator);
	        }
	    }
	    else 
	    	return null;
	    return content;
	}
	public boolean hasnextrow(){
		return rowIterator.hasNext();
	}
	public void fileclose(){
		try {
			this.inp.close();
			this.inp=null;
			this.wb = null;
		    this.sheet = null;
		    this.objFormulaEvaluator = null;
		    this.rowIterator =null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
