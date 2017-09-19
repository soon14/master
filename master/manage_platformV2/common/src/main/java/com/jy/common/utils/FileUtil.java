package com.jy.common.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jy.entity.system.channels.CashInfo;
import com.jy.entity.system.channels.FilePO;
import com.jy.entity.system.channels.ThirdPayInfo;
import com.jy.entity.system.channels.TicketInfoPO;
import com.jy.entity.system.channels.VoucherInfo;

public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtils.class);

    private static final char COLON_CHAR = ':';
    private static final String UNC_PREFIX = "//";
    private static final String SLASH = "/";
    private static final char SLASH_CHAR = '/';
    private static final char BACKSLASH_CHAR = '\\';

	/** 当前目录记号："." */
    public static final String CURRENT_DIR = ".";

	/** 上级目录记号：".." */
    public static final String UP_LEVEL_DIR = "..";

    /**
	 * 扩展名分隔符
	 */
    private static final char EXTENSION_SEPARATOR = '.';

    /**
	 * UNIX文件路径分隔符
	 */
    private static final char UNIX_SEPARATOR = '/';

    /**
	 * WINDOWS文件路径分隔符
	 */
    private static final char WINDOWS_SEPARATOR = '\\';

	/** 临时文件前缀 */
    private static final String TEMP_FILE_PREFIX = "temp_file_prefix-";

    /**
	 * 从URL中取得文件，如果URL为空，或不代表一个文件, 则返回null
	 *
	 * @param url URL
	 * @return 文件, 如果URL为空，或不代表一个文件, 则返回null
	 */
    public static File toFile(URL url) {
        if (url == null) {
            return null;
        }

        if (!"file".equals(url.getProtocol())) {
            return null;
        }

        String path = url.getPath();

        return (path != null) ? new File(path) : null;

    }

    /**
	 * 判断文件是否存在，如果path为null，则返回false
	 *
	 * @param path 文件路径
	 * @return 如果存在返回true
	 */
    public static boolean exist(String path) {
        return (path == null) ? false : new File(path).exists();
    }

    /**
	 * 判断文件是否存在，如果file为null，则返回false
	 *
	 * @param file 文件
	 * @return 如果存在返回true
	 */
    public static boolean exist(File file) {
        return (file == null) ? false : file.exists();
    }

    /**
	 * 是否存在匹配文件
	 *
	 * @param directory 文件夹路径
	 * @param regexp 文件夹中所包含文件名的正则表达式
	 * @return 如果存在匹配文件返回true
	 */
    public static boolean exist(String directory, String regexp) {
        File file = new File(directory);
        if (!file.exists()) {
            return false;
        }

        String[] fileList = file.list();
        if (fileList == null) {
            return false;
        }

        for (String fileName : fileList) {
            if (fileName.matches(regexp)) {
                return true;
            }

        }
        return false;
    }

    /**
	 * 判断是否为目录，如果path为null，则返回false
	 *
	 * @param path 文件路径
	 * @return 如果为目录true
	 */
    public static boolean isDirectory(String path) {
        return (path == null) ? false : new File(path).isDirectory();
    }

    /**
	 * 判断是否为目录，如果file为null，则返回false
	 *
	 * @param file 文件
	 * @return 如果为目录true
	 */
    public static boolean isDirectory(File file) {
        return (file == null) ? false : file.isDirectory();
    }

    /**
	 * 判断是否为文件，如果path为null，则返回false
	 *
	 * @param path 文件路径
	 * @return 如果为文件true
	 */
    public static boolean isFile(String path) {
        return (path == null) ? false : new File(path).isDirectory();
    }

    /**
	 * 判断是否为文件，如果file为null，则返回false
	 *
	 * @param file 文件
	 * @return 如果为文件true
	 */
    public static boolean isFile(File file) {
        return (file == null) ? false : file.isDirectory();
    }

    /**
	 * 列出文件目录dir下以suffix结尾的子文件集合，非递归 <p> 如果dir为null或不存在，则返回null <p> 如果dir不为目录，则返回null <p> 如果 suffix为null或""，则代表返回所有子文件
	 *
	 * @param dir 文件目录
	 * @param suffix 文件后缀
	 * @return 目录dir下以suffix结尾的子文件集合，非递归
	 */
    public static File[] listDirSuffixFiles(File dir, final String suffix) {
        if (dir == null) {
            return null;
        }
        if (!dir.exists() || dir.isFile()) {
            return null;
        }

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return StringUtils.isEmpty(suffix) ? true : (file.getName().endsWith(suffix));
            }
        });
    }

    /**
	 * 列出文件目录dirPath下以suffix结尾的子文件集合，非递归 <p> 如果dirPath为null或不存在，则返回null <p> 如果dirPath不为目录，则返回null <p> 如果 suffix为null或""，则代表返回所有子文件
	 *
	 * @param dirPath 文件目录
	 * @param suffix 文件后缀
	 * @return 目录dirPath下以suffix结尾的子文件集合，非递归
	 */
    public static File[] listDirSuffixFiles(String dirPath, final String suffix) {
        if (!exist(dirPath)) {
            return null;
        }
        File dir = new File(dirPath);

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return StringUtils.isEmpty(suffix) ? true : (file.getName().endsWith(suffix));
            }
        });
    }

    /**
	 * 列出文件目录dir下满足所有条件conditions的子文件集合，非递归 <p> 如果dir为null或不存在，则返回null <p> 如果dir不为目录，则返回null <p> 如果conditions为null，则认为无条件限制
	 *
	 * @param dir 文件目录
	 * @param conditions 过滤条件
	 * @return 目录dir下满足所有条件conditions的子文件集合，非递归
	 */
    public static File[] listDirAllConditionFiles(File dir, final boolean...conditions) {
        if (dir == null) {
            return null;
        }
        if (!dir.exists() || dir.isFile()) {
            return null;
        }

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (null == conditions || conditions.length == 0) {
                    return true;
                }
                for (boolean condition : conditions) {
                    if (!condition) {
                        return false;
                    }
                }

                return true;
            }
        });
    }

    /**
	 * 列出文件目录dirPath下满足所有条件conditions的子文件集合，非递归 <p> 如果dirPath为null或不存在，则返回null <p> 如果dirPath不为目录，则返回null <p> 如果conditions为null，则认为无条件限制
	 *
	 * @param dirPath 文件目录
	 * @param conditions 过滤条件
	 * @return 目录dirPath下满足所有条件conditions的子文件集合，非递归
	 */
    public static File[] listDirAllConditionFiles(String dirPath, final boolean...conditions) {
        if (!exist(dirPath)) {
            return null;
        }
        File dir = new File(dirPath);

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (null == conditions || conditions.length == 0) {
                    return true;
                }
                for (boolean condition : conditions) {
                    if (!condition) {
                        return false;
                    }
                }

                return true;
            }
        });
    }

    /**
	 * 列出文件目录dir下满足任一条件conditions的子文件集合，非递归 <p> 如果dir为null或不存在，则返回null <p> 如果dir不为目录，则返回null <p> 如果conditions为null，则认为无条件限制
	 *
	 * @param dir 文件目录
	 * @param conditions 过滤条件
	 * @return 目录dir下满足任一条件conditions的子文件集合，非递归
	 */
    public static File[] listDirAnyConditionFiles(File dir, final boolean...conditions) {
        if (dir == null) {
            return null;
        }
        if (!dir.exists() || dir.isFile()) {
            return null;
        }

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (null == conditions || conditions.length == 0) {
                    return true;
                }
                for (boolean condition : conditions) {
                    if (condition) {
                        return true;
                    }
                }

                return false;
            }
        });
    }

    /**
	 * 列出文件目录dirPath下满足任一条件conditions的子文件集合，非递归 <p> 如果dirPath为null或不存在，则返回null <p> 如果dirPath不为目录，则返回null <p> 如果conditions为null，则认为无条件限制
	 *
	 * @param dirPath 文件目录
	 * @param conditions 过滤条件
	 * @return 目录dirPath下满足任一条件conditions的子文件集合，非递归
	 */
    public static File[] listDirAnyConditionFiles(String dirPath, final boolean...conditions) {
        if (!exist(dirPath)) {
            return null;
        }
        File dir = new File(dirPath);

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (null == conditions || conditions.length == 0) {
                    return true;
                }
                for (boolean condition : conditions) {
                    if (condition) {
                        return true;
                    }
                }

                return false;
            }
        });
    }

    /**
	 * 简单工厂
	 *
	 * @param filename 文件名
	 * @return new File(filename)
	 */
    public static File file(String filename) {
        if (filename == null) {
            return null;
        }
        return new File(filename);
    }

    /**
	 * 简单工厂
	 *
	 * @param parent 父目录
	 * @param child 子文件
	 * @return new File(parent, child);
	 */
    public static File file(File parent, String child) {
        if (child == null) {
            return null;
        }

        return new File(parent, child);
    }

    /**
	 * 创建文件，不管文件层级，均可创建
	 *
	 * @param path 文件路径
	 * @return 是否创建成功，如果path为空或者path为null ,则返回false
	 * @throws IOException
	 */
    public static boolean createFile(String path) throws IOException {
        return createFile(path, false);
    }

    /**
	 * 创建文件，不管文件层级，均可创建
	 *
	 * @param path 文件路径
	 * @param override 是否覆盖
	 * @return 是否创建成功，如果path为空或者path为null ,则返回false
	 * @throws IOException
	 */
    public static boolean createFile(String path, boolean override) throws IOException {
        if (path == null) {
            return false;
        }

        File file = new File(path);
        if (file.exists() && !override) {
            return false;
        }

        if (file.isDirectory()) {
            return file.mkdirs();
        }

        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        return file.createNewFile();
    }

    /**
	 * 创建文件夹，不管文件层级，均可创建
	 *
	 * @param path 文件路径
	 * @param override 是否覆盖
	 * @return 是否创建成功，如果path为空或者path为null ,则返回false
	 */
    public static boolean createDir(String path, boolean override) {
        if (path == null) {
            return false;
        }

        File file = new File(path);
        if (file.exists() && !override) {
            return false;
        }

        return file.mkdirs();
    }

    /**
	 * 创建文件夹，不管文件层级，均可创建
	 *
	 * @param path 文件路径
	 * @return 是否创建成功，如果path为空或者path为null ,则返回false
	 */
    public static boolean createDir(String path) {
        return createDir(path, false);
    }

    /**
	 * 创建文件路径的父文件夹，不管文件层级，均可创建
	 *
	 * @param path 文件路径
	 * @return 是否创建成功，如果path为空或者path为null ,则返回false
	 */
    public static boolean createParentDir(String path) {
        return createParentDir(path, false);
    }

    public static boolean createParentDir(File file) {
        return createParentDir(file, false);
    }

    /**
	 * 创建文件路径的父文件夹，不管文件层级，均可创建
	 *
	 * @param path 文件路径
	 * @param override 是否覆盖
	 * @return 是否创建成功，如果path为空或者path为null ,则返回false
	 */
    public static boolean createParentDir(String path, boolean override) {
        if (path == null) {
            return false;
        }

        return createDir(new File(path).getParent(), override);
    }

    public static boolean createParentDir(File file, boolean override) {
        if (file == null) {
            return false;
        }

        return createDir(file.getParent(), override);
    }

    /**
	 * 刪除文件
	 *
	 * @param file 文件
	 * @return 删除成功返回true，否则返回false
	 */
    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }

        return file.delete();
    }

    /**
	 * 上传文件
	 *
	 * @param
	 * @return 删除成功返回true，否则返回false
	 */
    public static FilePO uploadFile(String payWay,String filePath, MultipartFile ticketfile,String importCount) throws Exception {
		logger.info("上传文件开始");
        FilePO vo = new FilePO();
		DiskFileItemFactory factory = new DiskFileItemFactory();// 1、创建一个DiskFileItemFactory工厂
		ServletFileUpload upload = new ServletFileUpload(factory);// 2、创建一个文件上传解析器
		upload.setHeaderEncoding("UTF-8");// 2、创建一个文件上传解析器
		logger.info("监听文件上传进度");
		fileUploadListen(upload);// 4、监听文件上传进度
		// 5、检查上传文件是否超过大小
		logger.info("检查上传文件是否超过大小");
        if (checkFileSize(upload, 1024, 10)) {
			logger.error("超过系统上传大小上限");
            return null;
        }
        CommonsMultipartFile cf = (CommonsMultipartFile)ticketfile;
        FileItem item =  cf.getFileItem();
		// 如果fileitem中封装的是上传文件
		String filename = item.getName();
		vo.setOriginalName(filename);// 得到上传的文件名称，
		logger.info("上传文件名：" + filename);
        if (filename == null || filename.trim().equals("")) {
            return null;
        }
		// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
		// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
        filename = filename.substring(filename.lastIndexOf("\\") + 1);
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        vo.setSuffix(suffix);
		// 检查文件格式
        if (!checkFileSuffix(filename, "xlsx,xls,csv")) {
			logger.error("上传文件格式不正确,文件名:" + filename);
            return null;
        }

		filename = makeFileName(payWay, suffix, importCount);// 重新生成文件名
        vo.setBatchNo(filename);
		logger.info("重新生成文件名：" + filename);
        System.out.println(filename);
		InputStream in = item.getInputStream(); // 获取item中的上传文件的输入流
		FileOutputStream out = new FileOutputStream(filePath + "\\" + filename);// 创建一个文件输出流
		byte buffer[] = new byte[1024];// 创建一个缓冲区
		int len = 0; // 判断输入流中的数据是否已经读完的标识
		// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
        while ((len = in.read(buffer)) > 0) {
			// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录当中
            out.write(buffer, 0, len);
        }
		in.close();// 关闭输入流
		out.close(); // 关闭输出流
		item.delete();// 删除处理文件上传时生成的临时文件
		logger.info(filename + "文件上传成功！");
		vo.setFileName(filePath + "\\" + filename);

        return vo;
    }

    /**
	 * 创建文件或者文件目录
	 *
	 * @param file 带有路径的文件名或者文件路径
	 * @author Dingj
	 */
    public static File createFile(File file) {
        if (!file.exists() && !file.isDirectory()) {
			logger.info(file + "目录不存在，需要创建");
			file.mkdirs(); // 创建目录
        }
        return file;
    }

    /**
	 * 监听文件上传进度
	 *
	 * @param upload 上传文件
	 */
    public static void fileUploadListen(ServletFileUpload upload) {
        upload.setProgressListener(new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int arg2) {
				logger.info("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
            }
        });
    }

    /**
	 * 检查上传文件大小
	 *
	 * @param upload 上传文件
	 * @param bytes 单个文件大小值
	 * @param num 上传总量大小
	 */
    public static boolean checkFileSize(ServletFileUpload upload, int bytes, long num) {
        return (upload.getFileSizeMax() > (bytes * bytes) || upload.getSizeMax() > (bytes * bytes * num));
    }

    /**
	 * 检查文件格式是否正确
	 *
	 * @param filename 文件名
	 * @param suffix 要求的文件格式
	 */
    public static boolean checkFileSuffix(String filename, String suffix) {
		// 得到上传文件的扩展名
        String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
		// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
        if (!suffix.contains(fileExtName))
            return false;
        return true;
    }

    /**
	 * 根据原文件名后缀名格式生成新文件名
	 *
	 * @param suffix 文件后缀名
	 * @param count
	 * @return
	 */
    public static String makeFileName(String payWay,String suffix,String count) {
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        String title ="";
        if(payWay.equals("0")){
            title= "cpcp_ticketInfo";
        }else if(payWay.equals("1")){
            title= "weChat_payInfo";
        }else if(payWay.equals("3")){
            title= "unionPay_payInfo";
        }else if(payWay.equals("2")){
            title= "daysPass_payInfo";
        } else if (payWay.equals("4")) {
			title = "cash_payInfo";
		} else if (payWay.equals("5")) {
			title = "TC_ VoucherInfo";
		}
        return title + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_"+count + "." + suffix;
    }

    /**
	 * 读取出票明细并解析
	 *
	 * @param po 带具体路径的文件名
	 * @param userName 文件后缀名
	 * @return
	 */
    public static List<TicketInfoPO> readExcel(FilePO po,String userName) throws Exception {
        String originalName = po.getOriginalName();
        String ticketType =originalName.substring(0,4);
        InputStream is = new FileInputStream(po.getFileName());
        List<TicketInfoPO> result = new ArrayList<TicketInfoPO>();
        int num = 0;
        if (po.getSuffix().equals("xlsx")) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            int sheetSize = xssfWorkbook.getNumberOfSheets();
			// 循环每一页，并处理当前页
            for (int numSheet = 0; numSheet < sheetSize; numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    num++;
                    continue;
                }
                long rowNums = xssfSheet.getLastRowNum() <= 0 ? 0 : (xssfSheet.getLastRowNum() + 1);
				// 处理当前页
                for (int rowNum = 1; rowNum < rowNums; rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    TicketInfoPO vo = new TicketInfoPO();
					// 得到表单数据
					vo.setMachionNo(getStringVal(xssfRow.getCell(0)));// 投注机编号
					vo.setIssueNo(getStringVal(xssfRow.getCell(14)));// 彩票期次
					vo.setTicketNo(getStringVal(xssfRow.getCell(4))); // 用户方案编号
					vo.setFlowCode(getStringVal(xssfRow.getCell(5))); // 系统流水号
					vo.setTicketMoney(getStringVal(xssfRow.getCell(3))); // 票面资金
					vo.setTicketDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getStringVal(xssfRow.getCell(11)))); // 出票时间
                    vo.setImportDateTime(new Date());
                    vo.setImportDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
                    vo.setImportUser(userName);
                    vo.setBatchNo(po.getBatchNo());
                    vo.setIsOnline("1");
                    /**设置出票类型*/
                    if(ticketType.equals("8001")){
                        vo.setTicketType("8001");
                    }else if(ticketType.equals("8002")){
                        vo.setTicketType("8002");
                    }else{
                        vo.setTicketType("8000");
                    }
                    result.add(vo);
                }
            }
            if (num == sheetSize) {
				logger.error("上传表单无数据,请核实!");
                return result;
            }
        }
        if (po.getSuffix().equals("xls")) {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            int sheetSize = hssfWorkbook.getNumberOfSheets();
			// 循环每一页，并处理当前页
            for (int numSheet = 0; numSheet < sheetSize; numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    num++;
                    continue;
                }
                long rowNums = hssfSheet.getLastRowNum() <= 0 ? 0 : (hssfSheet.getLastRowNum() + 1);
				// 处理当前页
                for (int rowNum = 1; rowNum < rowNums; rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    TicketInfoPO vo = new TicketInfoPO();
					// 得到表单数据
					vo.setMachionNo(getStringVal(hssfRow.getCell(0)));// 投注机编号
					vo.setIssueNo(getStringVal(hssfRow.getCell(14)));// 彩票期次
					vo.setTicketNo(getStringVal(hssfRow.getCell(4))); // 用户方案编号
					vo.setFlowCode(getStringVal(hssfRow.getCell(5))); // 系统流水号
					vo.setTicketMoney(getStringVal(hssfRow.getCell(3))); // 票面资金
					vo.setTicketDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getStringVal(hssfRow.getCell(11)))); // 出票时间
                    vo.setImportDateTime(new Date());
                    vo.setImportDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
                    vo.setImportUser(userName);
                    vo.setBatchNo(po.getBatchNo());
                    vo.setIsOnline("1");
                    /**设置出票类型*/
                    if(ticketType.equals("8001")){
                        vo.setTicketType("8001");
                    }else if(ticketType.equals("8002")){
                        vo.setTicketType("8002");
                    }else{
                        vo.setTicketType("8000");
                    }
                    result.add(vo);
                }
            }
            if (num == sheetSize) {
				logger.error("上传表单无数据,请核实!");
                return result;
            }
        }
        if (po.getSuffix().equals("csv")) {
            CsvReader reader = new CsvReader(po.getFileName(), ',', Charset.forName("GBK"));
            int index = 0;
            while (reader.readRecord()) {
                String[] stringList = reader.getValues();
                if (index > 0) {
                    TicketInfoPO vo = new TicketInfoPO();
                    // 得到表单数据
                    vo.setMachionNo(stringList[0]);// 投注机编号
                    vo.setIssueNo(stringList[14]);// 彩票期次
                    vo.setTicketNo(stringList[4]); // 用户方案编号
                    vo.setFlowCode(stringList[5]); // 系统流水号
                    vo.setTicketMoney(stringList[3]); // 票面资金
                    vo.setTicketDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringList[11])); // 出票时间
                    vo.setImportDateTime(new Date());
                    vo.setImportDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
                    vo.setImportUser(userName);
                    vo.setBatchNo(po.getBatchNo());
                    vo.setIsOnline("1");
                    /**设置出票类型*/
                    if(ticketType.equals("8001")){
                        vo.setTicketType("8001");
                    }else if(ticketType.equals("8002")){
                        vo.setTicketType("8002");
                    }else{
                        vo.setTicketType("8000");
                    }
                    result.add(vo);
                    result.add(vo);
                }
                index++;
            }
        }
        return result;
    }

	/**
	 * 读取兑奖明细并解析
	 *
	 * @param po 带具体路径的文件名
	 * @param userName 文件后缀名
	 * @return
	 */
	public static List<CashInfo> readCashPay(FilePO po, String userName,  String payWay) throws Exception {
        String originalName = po.getOriginalName();
        String ticketType =originalName.substring(0,4);
		InputStream is = new FileInputStream(po.getFileName());
		List<CashInfo> result = new ArrayList<CashInfo>();
		int num = 0;
		if (po.getSuffix().equals("xlsx")) {
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			int sheetSize = xssfWorkbook.getNumberOfSheets();
			// 循环每一页，并处理当前页
			for (int numSheet = 0; numSheet < sheetSize; numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					num++;
					continue;
				}
				long rowNums = xssfSheet.getLastRowNum() <= 0 ? 0 : (xssfSheet.getLastRowNum() + 1);
				// 处理当前页
				for (int rowNum = 1; rowNum < rowNums; rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					CashInfo vo = new CashInfo();
					// 得到表单数据
					vo.setBatchNo(po.getBatchNo()); // 批次号
					vo.setPayWay(payWay);// 第三单方支付渠道
					vo.setMachineNumber(getStringVal(xssfRow.getCell(0)));// 机器编号
					vo.setTicketNumber(getStringVal(xssfRow.getCell(2)));
					vo.setSystemSerialNumber(getStringVal(xssfRow.getCell(3)));// 系统流水号
					vo.setMachineAfterTaxBonus(getStringVal(xssfRow.getCell(7)));// 票机税后奖金
					vo.setNetBeforTaxBonus(getStringVal(xssfRow.getCell(8)));// 网站税前奖金
					vo.setNetAfterTaxBonus(getStringVal(xssfRow.getCell(9)));// 网站税后奖金
					vo.setPrizeType(getStringVal(xssfRow.getCell(12)));// 大小奖
					vo.setPrizeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getStringVal(xssfRow.getCell(13))));// 兑奖时间
					vo.setIssueNumber(getStringVal(xssfRow.getCell(16)));// 期次
					vo.setBettingAmount(getStringVal(xssfRow.getCell(19)));// 投注金额
					vo.setTickDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getStringVal(xssfRow.getCell(21))));// 出票成功时间
					vo.setCreateDate(new Date());// 创建时间
					vo.setCreateUser(userName);// 当前用户
                    /**设置出票类型*/
                    if(ticketType.equals("8001")){
                        vo.setTicketType("8001");
                    }else if(ticketType.equals("8002")){
                        vo.setTicketType("8002");
                    }else{
                        vo.setTicketType("8000");
                    }
					result.add(vo);
				}
			}
			if (num == sheetSize) {
				logger.error("上传表单无数据,请核实!");
				return result;
			}
		}
		if (po.getSuffix().equals("xls")) {
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			int sheetSize = hssfWorkbook.getNumberOfSheets();
			// 循环每一页，并处理当前页
			for (int numSheet = 0; numSheet < sheetSize; numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					num++;
					continue;
				}
				long rowNums = hssfSheet.getLastRowNum() <= 0 ? 0 : (hssfSheet.getLastRowNum() + 1);
				// 处理当前页
				for (int rowNum = 1; rowNum < rowNums; rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					CashInfo vo = new CashInfo();
					// 得到表单数据
					vo.setBatchNo(po.getBatchNo()); // 批次号
					vo.setPayWay(payWay);// 第三单方支付渠道
					vo.setMachineNumber(getStringVal(hssfRow.getCell(0)));// 机器编号
					vo.setTicketNumber(getStringVal(hssfRow.getCell(2)));
					vo.setSystemSerialNumber(getStringVal(hssfRow.getCell(3)));// 系统流水号
					vo.setMachineAfterTaxBonus(getStringVal(hssfRow.getCell(7)));// 票机税后奖金
					vo.setNetBeforTaxBonus(getStringVal(hssfRow.getCell(8)));// 网站税前奖金
					vo.setNetAfterTaxBonus(getStringVal(hssfRow.getCell(9)));// 网站税后奖金
					vo.setPrizeType(getStringVal(hssfRow.getCell(12)));// 大小奖
					vo.setPrizeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getStringVal(hssfRow.getCell(13))));// 兑奖时间
					vo.setIssueNumber(getStringVal(hssfRow.getCell(16)));// 期次
					vo.setBettingAmount(getStringVal(hssfRow.getCell(19)));// 投注金额
					vo.setTickDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getStringVal(hssfRow.getCell(21))));// 出票成功时间
					vo.setCreateDate(new Date());
					vo.setCreateUser(userName);// 当前用户
                    /**设置出票类型*/
                    if(ticketType.equals("8001")){
                        vo.setTicketType("8001");
                    }else if(ticketType.equals("8002")){
                        vo.setTicketType("8002");
                    }else{
                        vo.setTicketType("8000");
                    }
					result.add(vo);
				}
			}
			if (num == sheetSize) {
				logger.error("上传表单无数据,请核实!");
				return result;
			}
		}
        if (po.getSuffix().equals("csv")) {
            CsvReader reader = new CsvReader(po.getFileName(), ',', Charset.forName("UTF-8"));
            int index = 0;
            while (reader.readRecord()) {
                String[] stringList = reader.getValues();
                if (index > 0) {
                    CashInfo vo = new CashInfo();
                    // 得到表单数据
                    vo.setBatchNo(po.getBatchNo()); // 批次号
                    vo.setPayWay(payWay);// 第三单方支付渠道
                    vo.setMachineNumber(stringList[0]);// 机器编号
                    vo.setTicketNumber(stringList[2]);
                    vo.setSystemSerialNumber(stringList[3]);// 系统流水号
                    vo.setMachineAfterTaxBonus(stringList[7]);// 票机税后奖金
                    vo.setNetBeforTaxBonus(stringList[8]);// 网站税前奖金
                    vo.setNetAfterTaxBonus(stringList[9]);// 网站税后奖金
                    vo.setPrizeType(stringList[12]);// 大小奖
                    vo.setPrizeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringList[13]));// 兑奖时间
                    vo.setIssueNumber(stringList[16]);// 期次
                    vo.setBettingAmount(stringList[19]);// 投注金额
                    vo.setTickDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringList[21]));// 出票成功时间
                    vo.setCreateDate(new Date());
                    vo.setCreateUser(userName);// 当前用户
                    /**设置出票类型*/
                    if(ticketType.equals("8001")){
                        vo.setTicketType("8001");
                    }else if(ticketType.equals("8002")){
                        vo.setTicketType("8002");
                    }else{
                        vo.setTicketType("8000");
                    }
                    result.add(vo);
                }
                index++;
            }
        }
		return result;
	}

    /**
	 * 读取得仕通类型文件并解析
	 *
	 * @param fileName 带具体路径的文件名
	 * @param suffix 文件后缀名
	 * @return
	 */
    public static List<ThirdPayInfo> readDaysPass(String fileName, String suffix,String payWay,String batchNo) throws Exception {
        InputStream is = new FileInputStream(fileName);
        List<ThirdPayInfo> result = new ArrayList<ThirdPayInfo>();
        int num = 0;
        if (suffix.equals("xlsx")) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            int sheetSize = xssfWorkbook.getNumberOfSheets();
			// 循环每一页，并处理当前页
            for (int numSheet = 0; numSheet < sheetSize; numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    num++;
                    continue;
                }
                long rowNums = xssfSheet.getLastRowNum() <= 0 ? 0 : (xssfSheet.getLastRowNum());
				// 处理当前页
                for (int rowNum = 4; rowNum < rowNums; rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    ThirdPayInfo vo = new ThirdPayInfo();
					// 得到表单数据
					vo.setBatchNo(batchNo); // 批次号
					vo.setPayNo(getStringVal(xssfRow.getCell(7)).trim()); // 商户支付订单号
					vo.setPayFlowNo(getStringVal(xssfRow.getCell(6))); // 交易流水号
					vo.setTransMoney(getStringVal(xssfRow.getCell(9))); // 交易金额
					vo.setPayWay(payWay); // 第三单方支付渠道
                    String demo1 =getStringVal(xssfRow.getCell(1))+getStringVal(xssfRow.getCell(2));
                    Date demo2 = new SimpleDateFormat("yyyyMMddHHmmss").parse(demo1);
					vo.setTradeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(demo2)); // 交易时间
					vo.setOpenId(getStringVal(xssfRow.getCell(0))); // 第三方账户识别号
                    result.add(vo);
                }
            }
            if (num == sheetSize) {
				logger.error("上传表单无数据,请核实!");
                return result;
            }
        }
        if (suffix.equals("xls")) {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            int sheetSize = hssfWorkbook.getNumberOfSheets();
			// 循环每一页，并处理当前页
            for (int numSheet = 0; numSheet < 1; numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    num++;
                    continue;
                }
                long rowNums = hssfSheet.getLastRowNum() <= 0 ? 0 : (hssfSheet.getLastRowNum());
				// 处理当前页
                for (int rowNum = 4; rowNum < rowNums; rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    ThirdPayInfo vo = new ThirdPayInfo();
					// 得到表单数据
					vo.setBatchNo(batchNo); // 批次号
					vo.setPayNo(getStringVal(hssfRow.getCell(7)).trim()); // 商户支付订单号
					vo.setPayFlowNo(getStringVal(hssfRow.getCell(6))); // 交易流水号
					vo.setTransMoney(getStringVal(hssfRow.getCell(9))); // 交易金额
					vo.setPayWay(payWay); // 第三单方支付渠道
                    String demo1 =getStringVal(hssfRow.getCell(1))+getStringVal(hssfRow.getCell(2));
                    Date demo2 = new SimpleDateFormat("yyyyMMddHHmmss").parse(demo1);
					vo.setTradeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(demo2)); // 交易时间
					vo.setOpenId(getStringVal(hssfRow.getCell(0))); // 第三方账户识别号
                    result.add(vo);
                }
            }
            if (num == sheetSize) {
				logger.error("上传表单无数据,请核实!");
                return result;
            }
        }
        return result;
    }

//    public static void main(String[] args) {
//        FileUtil f = new FileUtil();
//        try {
////            f.readUnionPay("G:\\mt\\20170221\\weChat_payInfo_20170221161841_02.csv","csv","0","hkjhsdkfhs");
//            f.readDaysPass("G:\\mt\\20170221\\weChat_payInfo_20170221161841_03.xls","xls","0","hkjhsdkfhs");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
	 * 读取微信类型文件并解析
	 *
	 * @param fileName 带具体路径的文件名
	 * @param suffix 文件后缀名
	 * @return
	 */
    public static List<ThirdPayInfo> readWeChat(String fileName, String suffix,String payWay,String batchNo) throws Exception {
        List<ThirdPayInfo> result = new ArrayList<ThirdPayInfo>();
        if (suffix.equals("csv")) {
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(
//                            new FileInputStream(fileName),"GBK"
//                    )
//            );
            CsvReader reader = new CsvReader(fileName,',',Charset.forName("GBK"));
            int index =0;
            while(reader.readRecord()) {
                String []stringList = reader.getValues();
                if(index>=4){
                    ThirdPayInfo vo = new ThirdPayInfo();
					// 得到表单数据
					vo.setBatchNo(batchNo); // 批次号
					vo.setPayNo(stringList[2].replaceAll("`", "").trim()); // 商户支付订单号
					vo.setPayFlowNo(stringList[1].replaceAll("`", "")); // 交易流水号
					vo.setTransMoney(stringList[11]); // 交易金额
					vo.setPayWay(payWay); // 第三单方支付渠道
					vo.setTradeTime(stringList[0]); // 交易时间
					vo.setOpenId(stringList[6]); // 第三方账户识别号
                    if("买家已支付".equals(stringList[9].trim())){
                        result.add(vo);
                    }
                }
                index++;
            }
            if(result.size()==0){
                CsvReader readers = new CsvReader(fileName,',',Charset.forName("utf-8"));
                int indexs =0;
                while(readers.readRecord()) {
                    String []stringList = readers.getValues();
                    if(indexs>=4){
                        ThirdPayInfo vo = new ThirdPayInfo();
                        // 得到表单数据
                        vo.setBatchNo(batchNo); // 批次号
                        vo.setPayNo(stringList[2].replaceAll("`", "").trim()); // 商户支付订单号
                        vo.setPayFlowNo(stringList[1].replaceAll("`", "")); // 交易流水号
                        vo.setTransMoney(stringList[11]); // 交易金额
                        vo.setPayWay(payWay); // 第三单方支付渠道
                        vo.setTradeTime(stringList[0]); // 交易时间
                        vo.setOpenId(stringList[6]); // 第三方账户识别号
                        if("买家已支付".equals(stringList[9].trim())){
                            result.add(vo);
                        }
                    }
                    indexs++;
                }
            }
        }
        return result;
    }

    /**
	 * 读取银联类型文件并解析
	 *
	 * @param fileName 带具体路径的文件名
	 * @param suffix 文件后缀名
	 * @return
	 */
    public static List<ThirdPayInfo> readUnionPay(String fileName, String suffix,String payWay,String batchNo) throws Exception {
        List<ThirdPayInfo> result = new ArrayList<ThirdPayInfo>();
        if (suffix.equals("csv")) {
            CsvReader reader = new CsvReader(fileName,',',Charset.forName("GBK"));
            int index =0;
            while(reader.readRecord()) {
                String []stringList = reader.getValues();
                if(index>=1){
					if ("总笔数".equals(stringList[0])) {
                        return result;
                    }
                    ThirdPayInfo vo = new ThirdPayInfo();
					// 得到表单数据
					vo.setBatchNo(batchNo); // 批次号
					vo.setPayNo(stringList[1].trim()); // 商户支付订单号
					vo.setPayFlowNo(stringList[0]); // 交易流水号
					vo.setTransMoney(stringList[5]); // 交易金额
					vo.setPayWay(payWay); // 第三单方支付渠道
                    String demo1 =stringList[2];
                    Date demo2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse(demo1);
					vo.setTradeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(demo2)); // 交易时间
					vo.setOpenId(stringList[14]); // 第三方账户识别号
                    result.add(vo);
                }
                index++;
            }
        }
        return result;
    }
	
	/**
	 * 读取代金券明细并解析
	 *
	 * @param fileName 带具体路径的文件名
	 * @param suffix 文件后缀名
	 * @return
	 */
	public static List<VoucherInfo> readVoucherInfo(String batchNo, String fileName, String suffix, String userName) throws Exception {
		InputStream is = new FileInputStream(fileName);
		List<VoucherInfo> result = new ArrayList<VoucherInfo>();
		int num = 0;
		if (suffix.equals("xlsx")) {
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			int sheetSize = xssfWorkbook.getNumberOfSheets();
			// 循环每一页，并处理当前页
			for (int numSheet = 0; numSheet < sheetSize; numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					num++;
					continue;
				}
				long rowNums = xssfSheet.getLastRowNum() <= 0 ? 0 : (xssfSheet.getLastRowNum() + 1);
				// 处理当前页
				for (int rowNum = 1; rowNum < rowNums; rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					VoucherInfo vo = new VoucherInfo();
					// 得到表单数据
					vo.setBatchNo(batchNo);
					vo.setSchemeNo(getStringVal(xssfRow.getCell(1)));
					vo.setvNo(getStringVal(xssfRow.getCell(2)));
					vo.setvMoney(getStringVal(xssfRow.getCell(3)));
					String status = getStringVal(xssfRow.getCell(6));
					String vStatus = null;
					if (status.equals("已使用")) {
						vStatus = "1";
					} else if (status.equals("未使用")) {
						vStatus = "2";
					} else if (status.equals("已过期")) {
						vStatus = "3";
					} else {
						vStatus = "0";
					}
					vo.setvStatus(vStatus);
					String a = xssfRow.getCell(7) + "";
					if (null == xssfRow.getCell(7) || a.equals("")) {
						vo.setUsedDate(null);
					} else {
						vo.setUsedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getStringVal(xssfRow.getCell(7))));
						
					}
					vo.setUsedMachine(getStringVal(xssfRow.getCell(8)));
					String b = xssfRow.getCell(4) + "";
					if (null == xssfRow.getCell(4) || b.equals("")) {
						vo.setvStart(null);
					} else {
						vo.setvStart(new SimpleDateFormat("yyyy-MM-dd").parse(getStringVal(xssfRow.getCell(4))));
						
					}
					String c = xssfRow.getCell(5) + "";
					if (null == xssfRow.getCell(5) || c.equals("")) {
						vo.setvEnd(null);
					} else {
						vo.setvEnd(new SimpleDateFormat("yyyy-MM-dd").parse(getStringVal(xssfRow.getCell(5))));
					}
					vo.setvRecordDate(new Date());
					vo.setvRecordUser(userName);
					result.add(vo);
				}
			}
			if (num == sheetSize) {
				logger.error("上传表单无数据,请核实!");
				return result;
			}
		}
		return result;
	}

    /**
	 * xlsx获取单元格元素数据
	 */
    public static String getStringVal(XSSFCell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() ? "true" : "false";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }

    /**
	 * xls获取单元格元素数据
	 */
    public static String getStringVal(HSSFCell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() ? "true" : "false";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }
}
