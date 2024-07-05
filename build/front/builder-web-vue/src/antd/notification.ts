import {notification} from "ant-design-vue";

/**
 * 发送全局通知
 * @param params { type 类型 , message 消息 , description 内容, onClick 触发事件, onClose 关闭事件 }
 */
export const showNotification = (params: {
    type: "success" | "info" | "error" | "warning" | undefined;
    message: string;
    description: string;
    onClick?: () => void;
    onClose?: () => void;
}): boolean => {
    notification.open({
        type: params.type,
        message: params.message,
        description: params.description,
        onClick: params.onClick,
        onClose: params.onClose,
    });
    return true;
};

/**
 * 发送成功全局通知
 * @param params { message 消息 , description 内容, onClick 触发事件, onClose 关闭事件 }
 */
export const showSuccessNotification = (params: {
    message: string;
    description: string;
    onClick?: () => void;
    onClose?: () => void;
}): boolean => {
    notification.open({
        type: "success",
        message: params.message,
        description: params.description,
        onClick: params.onClick,
        onClose: params.onClose,
    });
    return true;
};

/**
 * 发送信息全局通知
 * @param params { message 消息 , description 内容, onClick 触发事件, onClose 关闭事件 }
 */
export const showInfoNotification = (params: {
    message: string;
    description: string;
    onClick?: () => void;
    onClose?: () => void;
}): boolean => {
    notification.open({
        type: "info",
        message: params.message,
        description: params.description,
        onClick: params.onClick,
        onClose: params.onClose,
    });
    return true;
};

/**
 * 发送错误全局通知
 * @param params { message 消息 , description 内容, onClick 触发事件, onClose 关闭事件 }
 */
export const showErrorNotification = (params: {
    message: string;
    description: string;
    onClick?: () => void;
    onClose?: () => void;
}): boolean => {
    notification.open({
        type: "error",
        message: params.message,
        description: params.description,
        onClick: params.onClick,
        onClose: params.onClose,
    });
    return true;
};

/**
 * 发送警告全局通知
 * @param params { message 消息 , description 内容, onClick 触发事件, onClose 关闭事件 }
 */
export const showWarningNotification = (params: {
    message: string;
    description: string;
    onClick?: () => void;
    onClose?: () => void;
}): boolean => {
    notification.open({
        type: "warning",
        message: params.message,
        description: params.description,
        onClick: params.onClick,
        onClose: params.onClose,
    });
    return true;
};
