module.exports = {
    extends: ["stylelint-config-standard", "stylelint-config-recess-order"],
    rules: {
        "at-rule-no-unknown": [
            true,
            {
                ignoreAtRules: ["mixin", "extend", "content", "include"],
            },
        ],
        indentation: 2,
        "no-descending-specificity": null, // 禁止特异性较低的选择器在特异性较高的选择器之后重写
        "declaration-block-trailing-semicolon": null, // 结尾申明分号
        "rule-empty-line-before": null, // 规则前方是否加上空格
    },
};
