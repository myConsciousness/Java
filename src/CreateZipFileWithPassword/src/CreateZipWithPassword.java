import java.io.File;
import java.io.IOException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * パスワード付きzipを自動生成するクラス
 * @author Kato Shinya
 * @class CreateZipWithPassword
 */
public class CreateZipWithPassword {

  private static int compressionMethod = Zip4jConstants.COMP_DEFLATE;
  private static int compressionLevel = Zip4jConstants.DEFLATE_LEVEL_NORMAL;
  private static int encryptionMethod = Zip4jConstants.ENC_METHOD_STANDARD;
  private static int aesKeyStrength = Zip4jConstants.AES_STRENGTH_256;

  /**
   * [CreateZipWithPassword コンストラクタ]
   * @method CreateZipWithPassword
   */
  public CreateZipWithPassword() {
  }

  /**
   * [main メイン処理部]
   * @method main
   * @param  args                     	[可変長引数]
   * @throws IOException
   * @throws ZipException
   */
  public static void main(String args[]) throws ZipException, IOException {

	  // 実行ファイルにする場合のみmain部の実装が必要
	  if (args.length > 3) {
		  zip(args[0], args[1], args[2], args[3]);
	  } else {
		  zip(args[0], args[1], args[2]);
	  }
  }

  /**
   * [zip パスワード付きzipを作成するメソッド]
   * @method main
   * @param  input                     	[zip化するファイル]
   * @param  output                    	[出力名]
   * @param  password                   [パスワード]
   * @param  fileNameCharset            [ファイル名の文字コード]
   * @throws ZipException               [ZipException]
   * @throws IOException                [IOException]
   * @throws ZipException				[ZipException]
   */
  public static void zip(String input, String output, String password)
	        throws ZipException, IOException, ZipException {
	  zip(input, output, password, "Shift-JIS");
  }

  /**
   * [zip パスワード付きzipを作成するメソッド]
   * @method main
   * @param  input                     	[zip化するファイル]
   * @param  output                    	[出力名]
   * @param  password                   [パスワード]
   * @param  fileNameCharset            [ファイル名の文字コード]
   * @throws ZipException               [ZipException]
   * @throws IOException                [IOException]
   * @throws ZipException				[ZipException]
   */
  public static void zip(String input, String output, String password, String fileNameCharset)
	        throws ZipException, IOException, ZipException {

    ZipFile zipFile = null;

	try {
		zipFile = new ZipFile(output);

	    if (!fileNameCharset.isEmpty()) {
	        zipFile.setFileNameCharset(fileNameCharset);
	    }

	    ZipParameters parameters = new ZipParameters();
	    parameters.setCompressionMethod(compressionMethod);
	    parameters.setCompressionLevel(compressionLevel);
	    parameters.setEncryptFiles(true);
	    parameters.setEncryptionMethod(encryptionMethod);
	    parameters.setAesKeyStrength(aesKeyStrength);
	    parameters.setPassword(password);

	    File inputFile = new File(input);
	    if (inputFile.isDirectory()) {
	       zipFile.createZipFileFromFolder(inputFile, parameters, false, 0);
	    } else {
	       zipFile.addFile(inputFile, parameters);
	    }
	} catch (ZipException e) {
		e.printStackTrace();
	}
  }
}
