export const strictEncode = (str: string): string => {
	return (
		encodeURIComponent(str)
			// 处理保留字符
			// 冒号
			.replace(/[:]/g, "%3A")
			// 斜杠
			.replace(/[/]/g, "%2F")
			// 问号
			.replace(/[?]/g, "%3F")
			// 井号
			.replace(/[#]/g, "%23")
			// 左方括号
			.replace(/[[]/g, "%5B")
			// 右方括号
			.replace(/[\]]/g, "%5D")
			// at符号
			.replace(/[@]/g, "%40")
			// 处理其他可能引起问题的字符
			.replace(/[.]/g, "%2E")
	); // 点号
};
