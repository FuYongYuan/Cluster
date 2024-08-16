module.exports = {
    root: true,
    env: {
        node: true,
    },
    extends: [
        "plugin:vue/vue3-essential",
        "eslint:recommended",
        "@vue/typescript/recommended",
    ],
    parserOptions: {
        ecmaVersion: 2020,
    },
    rules: {
        "no-console": process.env.NODE_ENV === "production" ? "warn" : "off",
        "no-debugger": process.env.NODE_ENV === "production" ? "warn" : "off",
        "@typescript-eslint/explicit-module-boundary-types": process.env.NODE_ENV === "production" ? "warn" : "off", // ts每个函数都要显式声明返回值
        "@typescript-eslint/no-explicit-any": process.env.NODE_ENV === "production" ? "warn" : "off", // 关闭any类型的警告
        // 单一单词忽略校验可做页面名称
        "vue/multi-word-component-names": ["error", {
            "ignores": ["Layout", "403", "404", "500", "Logo", "Home", "About", "Login", "Recover", "Register"]
        }],
    },
};
