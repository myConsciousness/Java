public class StringSplosion {
	public static void main(String[] args) {

		print(stringSplosion("test"));
		print(stringSplosion("code"));
		print(stringSplosion("Hello World"));
	}

	/**
	 * @param String [i] 変換対象文字列
	 * @return String 変換後文字列
	 * @throws なし
	 */
	private static String stringSplosion(String str) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			result.append(str.substring(0, i+1));
		}
		return result.toString();
	}

	private static void print(Object o) {
		System.out.println(o);
	}
}
