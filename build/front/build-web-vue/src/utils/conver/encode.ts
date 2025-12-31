export const strictEncode = (str: string): string => {
	return (
		encodeURIComponent(str)
			// 处理保留字符
			.replace(/[:]/g, "%3A") // 冒号
			.replace(/[\/]/g, "%2F") // 斜杠
			.replace(/[?]/g, "%3F") // 问号
			.replace(/[#]/g, "%23") // 井号
			.replace(/[[]/g, "%5B") // 左方括号
			.replace(/[\]]/g, "%5D") // 右方括号
			.replace(/[@]/g, "%40") // at符号
			// 处理其他可能引起问题的字符
			.replace(/[.]/g, "%2E")
	); // 点号
};
