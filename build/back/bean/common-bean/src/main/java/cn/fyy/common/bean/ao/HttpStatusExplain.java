package cn.fyy.common.bean.ao;

import org.springframework.http.HttpStatus;

import java.util.TreeMap;

/**
 * Html状态码
 *
 * @author fyy
 */
public class HttpStatusExplain {
    /**
     * 初始化
     */
    private HttpStatusExplain() {
        throw new IllegalStateException("HttpStatusExplain.class错误");
    }

    /**
     * 中文说明集合
     */
    private static final TreeMap<HttpStatus, String> chinese = new TreeMap<>();

    /*
      初始化中文说明集合
     */
    static {
        //http状态返回代码 1xx（临时响应）表示临时响应并需要请求者继续执行操作的状态代码。
        chinese.put(HttpStatus.CONTINUE, "（继续）请求者应当继续提出请求。 服务器返回此代码表示已收到请求的第一部分，正在等待其余部分。");
        chinese.put(HttpStatus.SWITCHING_PROTOCOLS, "（切换协议）请求者已要求服务器切换协议，服务器已确认并准备切换。");
        chinese.put(HttpStatus.PROCESSING, "（代理处理）由WebDAV（RFC 2518）扩展的状态码，代表处理将被继续执行。");
        //http状态返回代码 2xx （成功）表示成功处理了请求的状态代码。
        chinese.put(HttpStatus.OK, "（成功）服务器已成功处理了请求。 通常，这表示服务器提供了请求的网页。");
        chinese.put(HttpStatus.CREATED, "（已创建）请求成功并且服务器创建了新的资源。");
        chinese.put(HttpStatus.ACCEPTED, "（已接受）服务器已接受请求，但尚未处理。");
        chinese.put(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "（非授权信息）服务器已成功处理了请求，但返回的信息可能来自另一来源。");
        chinese.put(HttpStatus.NO_CONTENT, "（无内容）服务器成功处理了请求，但没有返回任何内容。");
        chinese.put(HttpStatus.RESET_CONTENT, "（重置内容）服务器成功处理了请求，但没有返回任何内容。");
        chinese.put(HttpStatus.PARTIAL_CONTENT, "（部分内容）服务器成功处理了部分 GET 请求。");
        chinese.put(HttpStatus.MULTI_STATUS, "（独立响应）由WebDAV(RFC 2518)扩展的状态码，代表之后的消息体将是一个XML消息，并且可能依照之前子请求数量的不同，包含一系列独立的响应代码。");
        //http状态返回代码 3xx （重定向）表示要完成请求，需要进一步操作。 通常，这些状态代码用来重定向。
        chinese.put(HttpStatus.MULTIPLE_CHOICES, "（多种选择）针对请求，服务器可执行多种操作。服务器可根据请求者(user agent)选择一项操作，或提供操作列表供请求者选择。");
        chinese.put(HttpStatus.MOVED_PERMANENTLY, "（永久移动）请求的网页已永久移动到新位置。服务器返回此响应（对 GET 或 HEAD 请求的响应）时，会自动将请求者转到新位置。");
        chinese.put(HttpStatus.FOUND, "（临时移动）服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。");
        chinese.put(HttpStatus.SEE_OTHER, "（查看其他位置）请求者应当对不同的位置使用单独的 GET 请求来检索响应时，服务器返回此代码。");
        chinese.put(HttpStatus.NOT_MODIFIED, "（未修改）自从上次请求后，请求的网页未修改过。服务器返回此响应时，不会返回网页内容。");
        chinese.put(HttpStatus.TEMPORARY_REDIRECT, "（临时重定向）服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。");
        //http状态返回代码 4xx（请求错误）这些状态代码表示请求可能出错，妨碍了服务器的处理。
        chinese.put(HttpStatus.BAD_REQUEST, "（错误请求）服务器不理解请求的语法。");
        chinese.put(HttpStatus.UNAUTHORIZED, "（未授权）请求要求身份验证。对于需要登录的网页，服务器可能返回此响应。");
        chinese.put(HttpStatus.FORBIDDEN, "（禁止）服务器拒绝请求。");
        chinese.put(HttpStatus.NOT_FOUND, "（未找到）服务器找不到请求的网页。");
        chinese.put(HttpStatus.METHOD_NOT_ALLOWED, "（方法禁用）禁用请求中指定的方法。");
        chinese.put(HttpStatus.NOT_ACCEPTABLE, "（不接受）无法使用请求的内容特性响应请求的网页。");
        chinese.put(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, "（需要代理授权）此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理。");
        chinese.put(HttpStatus.REQUEST_TIMEOUT, "（请求超时）服务器等候请求时发生超时。");
        chinese.put(HttpStatus.CONFLICT, "（冲突）服务器在完成请求时发生冲突。 服务器必须在响应中包含有关冲突的信息。");
        chinese.put(HttpStatus.GONE, "（已删除）如果请求的资源已永久删除，服务器就会返回此响应。");
        chinese.put(HttpStatus.LENGTH_REQUIRED, "（需要有效长度）服务器不接受不含有效内容长度标头字段的请求。");
        chinese.put(HttpStatus.PRECONDITION_FAILED, "（未满足前提条件）服务器未满足请求者在请求中设置的其中一个前提条件。");
        chinese.put(HttpStatus.PAYLOAD_TOO_LARGE, "（请求实体过大）服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。");
        chinese.put(HttpStatus.URI_TOO_LONG, "（请求的 URI 过长）请求的 URI（通常为网址）过长，服务器无法处理。");
        chinese.put(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "（不支持的媒体类型）请求的格式不受请求页面的支持。");
        chinese.put(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, "（请求范围不符合要求）如果页面无法提供请求的范围，则服务器会返回此状态代码。");
        chinese.put(HttpStatus.EXPECTATION_FAILED, "（未满足期望值）服务器未满足\"期望\"请求标头字段的要求。");
        chinese.put(HttpStatus.UNPROCESSABLE_ENTITY, "请求格式正确，但是由于含有语义错误，无法响应。");
        chinese.put(HttpStatus.LOCKED, "当前资源被锁定。（RFC 4918 WebDAV）");
        chinese.put(HttpStatus.FAILED_DEPENDENCY, "由于之前的某个请求发生的错误，导致当前请求失败，例如 PROPPATCH。（RFC 4918 WebDAV）");
        chinese.put(HttpStatus.TOO_EARLY, "状态码 425 Too Early 代表服务器不愿意冒风险来处理该请求，原因是处理该请求可能会被“重放”，从而造成潜在的重放攻击。（RFC 8470）");
        chinese.put(HttpStatus.UPGRADE_REQUIRED, "客户端应当切换到TLS/1.0。（RFC 2817）");
        chinese.put(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "该请求因法律原因不可用。（RFC 7725）");
        //http状态返回代码 5xx（服务器错误）这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。
        chinese.put(HttpStatus.INTERNAL_SERVER_ERROR, "（服务器内部错误）服务器遇到错误，无法完成请求。");
        chinese.put(HttpStatus.NOT_IMPLEMENTED, "（尚未实施）服务器不具备完成请求的功能。例如，服务器无法识别请求方法时可能会返回此代码。");
        chinese.put(HttpStatus.BAD_GATEWAY, "（错误网关）服务器作为网关或代理，从上游服务器收到无效响应。");
        chinese.put(HttpStatus.SERVICE_UNAVAILABLE, "（服务不可用）服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态。");
        chinese.put(HttpStatus.GATEWAY_TIMEOUT, "（网关超时）服务器作为网关或代理，但是没有及时从上游服务器收到请求。");
        chinese.put(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "（HTTP 版本不受支持）服务器不支持请求中所用的 HTTP 协议版本。");
        chinese.put(HttpStatus.VARIANT_ALSO_NEGOTIATES, "由《透明内容协商协议》（RFC 2295）扩展，代表服务器存在内部配置错误：被请求的协商变元资源被配置为在透明内容协商中使用自己，因此在一个协商处理中不是一个合适的重点。");
        chinese.put(HttpStatus.INSUFFICIENT_STORAGE, "服务器无法存储完成请求所必须的内容。这个状况被认为是临时的。WebDAV (RFC 4918)");
        chinese.put(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, "服务器达到带宽限制。这不是一个官方的状态码，但是仍被广泛使用。");
        chinese.put(HttpStatus.NOT_EXTENDED, "获取资源所需要的策略并没有被满足。（RFC 2774）");
    }

    /**
     * 获取中文说明
     *
     * @param httpStatus 请求状态
     * @return 中文说明
     */
    public static String getChinese(HttpStatus httpStatus) {
        return chinese.get(httpStatus);
    }
}
