package http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * http状态返回中文描述
 *
 * @author fyy
 */
@Getter
public enum HttpStatusChinese {
    //http状态返回代码 1xx（临时响应）表示临时响应并需要请求者继续执行操作的状态代码。
    CONTINUE(HttpStatus.CONTINUE, "（继续）请求者应当继续提出请求。 服务器返回此代码表示已收到请求的第一部分，正在等待其余部分。"),
    SWITCHING_PROTOCOLS(HttpStatus.SWITCHING_PROTOCOLS, "（切换协议）请求者已要求服务器切换协议，服务器已确认并准备切换。"),
    PROCESSING(HttpStatus.PROCESSING, "（代理处理）由WebDAV（RFC 2518）扩展的状态码，代表处理将被继续执行。"),

    //http状态返回代码 2xx （成功）表示成功处理了请求的状态代码。
    OK(HttpStatus.OK, "（成功）服务器已成功处理了请求。 通常，这表示服务器提供了请求的网页。"),
    CREATED(HttpStatus.CREATED, "（已创建）请求成功并且服务器创建了新的资源。"),
    ACCEPTED(HttpStatus.ACCEPTED, "（已接受）服务器已接受请求，但尚未处理。"),
    NON_AUTHORITATIVE_INFORMATION(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "（非授权信息）服务器已成功处理了请求，但返回的信息可能来自另一来源。"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "（无内容）服务器成功处理了请求，但没有返回任何内容。"),
    RESET_CONTENT(HttpStatus.RESET_CONTENT, "（重置内容）服务器成功处理了请求，但没有返回任何内容。"),
    PARTIAL_CONTENT(HttpStatus.PARTIAL_CONTENT, "（部分内容）服务器成功处理了部分 GET 请求。"),
    MULTI_STATUS(HttpStatus.MULTI_STATUS, "（独立响应）由WebDAV(RFC 2518)扩展的状态码，代表之后的消息体将是一个XML消息，并且可能依照之前子请求数量的不同，包含一系列独立的响应代码。"),

    //http状态返回代码 3xx （重定向）表示要完成请求，需要进一步操作。 通常，这些状态代码用来重定向。
    MULTIPLE_CHOICES(HttpStatus.MULTIPLE_CHOICES, "（多种选择）针对请求，服务器可执行多种操作。服务器可根据请求者(user agent)选择一项操作，或提供操作列表供请求者选择。"),
    MOVED_PERMANENTLY(HttpStatus.MOVED_PERMANENTLY, "（永久移动）请求的网页已永久移动到新位置。服务器返回此响应（对 GET 或 HEAD 请求的响应）时，会自动将请求者转到新位置。"),
    FOUND(HttpStatus.FOUND, "（临时移动）服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。"),
    SEE_OTHER(HttpStatus.SEE_OTHER, "（查看其他位置）请求者应当对不同的位置使用单独的 GET 请求来检索响应时，服务器返回此代码。"),
    NOT_MODIFIED(HttpStatus.NOT_MODIFIED, "（未修改）自从上次请求后，请求的网页未修改过。服务器返回此响应时，不会返回网页内容。"),
    TEMPORARY_REDIRECT(HttpStatus.TEMPORARY_REDIRECT, "（临时重定向）服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。"),

    //http状态返回代码 4xx（请求错误）这些状态代码表示请求可能出错，妨碍了服务器的处理。
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "（错误请求）服务器不理解请求的语法。"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "（未授权）请求要求身份验证。对于需要登录的网页，服务器可能返回此响应。"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "（禁止）服务器拒绝请求。"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "（未找到）服务器找不到请求的网页。"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "（方法禁用）禁用请求中指定的方法。"),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "（不接受）无法使用请求的内容特性响应请求的网页。"),
    PROXY_AUTHENTICATION_REQUIRED(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, "（需要代理授权）此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理。"),
    REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "（请求超时）服务器等候请求时发生超时。"),
    CONFLICT(HttpStatus.CONFLICT, "（冲突）服务器在完成请求时发生冲突。 服务器必须在响应中包含有关冲突的信息。"),
    GONE(HttpStatus.GONE, "（已删除）如果请求的资源已永久删除，服务器就会返回此响应。"),
    LENGTH_REQUIRED(HttpStatus.LENGTH_REQUIRED, "（需要有效长度）服务器不接受不含有效内容长度标头字段的请求。"),
    PRECONDITION_FAILED(HttpStatus.PRECONDITION_FAILED, "（未满足前提条件）服务器未满足请求者在请求中设置的其中一个前提条件。"),
    PAYLOAD_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, "（请求实体过大）服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。"),
    URI_TOO_LONG(HttpStatus.URI_TOO_LONG, "（请求的 URI 过长）请求的 URI（通常为网址）过长，服务器无法处理。"),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "（不支持的媒体类型）请求的格式不受请求页面的支持。"),
    REQUESTED_RANGE_NOT_SATISFIABLE(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, "（请求范围不符合要求）如果页面无法提供请求的范围，则服务器会返回此状态代码。"),
    EXPECTATION_FAILED(HttpStatus.EXPECTATION_FAILED, "（未满足期望值）服务器未满足\"期望\"请求标头字段的要求。"),
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "请求格式正确，但是由于含有语义错误，无法响应。"),
    LOCKED(HttpStatus.LOCKED, "当前资源被锁定。（RFC 4918 WebDAV）"),
    FAILED_DEPENDENCY(HttpStatus.FAILED_DEPENDENCY, "由于之前的某个请求发生的错误，导致当前请求失败，例如 PROPPATCH。（RFC 4918 WebDAV）"),
    TOO_EARLY(HttpStatus.TOO_EARLY, "状态码 425 Too Early 代表服务器不愿意冒风险来处理该请求，原因是处理该请求可能会被“重放”，从而造成潜在的重放攻击。（RFC 8470）"),
    UPGRADE_REQUIRED(HttpStatus.UPGRADE_REQUIRED, "客户端应当切换到TLS/1.0。（RFC 2817）"),
    UNAVAILABLE_FOR_LEGAL_REASONS(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "该请求因法律原因不可用。（RFC 7725）"),

    //http状态返回代码 5xx（服务器错误）这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "（服务器内部错误）服务器遇到错误，无法完成请求。"),
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "（尚未实施）服务器不具备完成请求的功能。例如，服务器无法识别请求方法时可能会返回此代码。"),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "（错误网关）服务器作为网关或代理，从上游服务器收到无效响应。"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "（服务不可用）服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态。"),
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "（网关超时）服务器作为网关或代理，但是没有及时从上游服务器收到请求。"),
    HTTP_VERSION_NOT_SUPPORTED(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "（HTTP 版本不受支持）服务器不支持请求中所用的 HTTP 协议版本。"),
    VARIANT_ALSO_NEGOTIATES(HttpStatus.VARIANT_ALSO_NEGOTIATES, "由《透明内容协商协议》（RFC 2295）扩展，代表服务器存在内部配置错误：被请求的协商变元资源被配置为在透明内容协商中使用自己，因此在一个协商处理中不是一个合适的重点。"),
    INSUFFICIENT_STORAGE(HttpStatus.INSUFFICIENT_STORAGE, "服务器无法存储完成请求所必须的内容。这个状况被认为是临时的。WebDAV (RFC 4918)"),
    BANDWIDTH_LIMIT_EXCEEDED(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, "服务器达到带宽限制。这不是一个官方的状态码，但是仍被广泛使用。"),
    NOT_EXTENDED(HttpStatus.NOT_EXTENDED, "获取资源所需要的策略并没有被满足。（RFC 2774）");

    /**
     * http状态返回代码
     */
    private final HttpStatus httpStatus;
    /**
     * 中文描述
     */
    private final String chineseDescription;

    /**
     * 构造器
     *
     * @param httpStatus         http状态码
     * @param chineseDescription 中文描述
     */
    HttpStatusChinese(HttpStatus httpStatus, String chineseDescription) {
        this.httpStatus = httpStatus;
        this.chineseDescription = chineseDescription;
    }

    /**
     * 根据http状态码获取中文描述
     *
     * @param httpStatus http状态码
     * @return 中文描述
     */
    public static String getChineseDescriptionByHttpStatus(HttpStatus httpStatus) {
        for (HttpStatusChinese status : HttpStatusChinese.values()) {
            if (status.getHttpStatus() == httpStatus) {
                return status.getChineseDescription();
            }
        }
        return null;
    }
}
