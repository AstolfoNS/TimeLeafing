package com.astolfo.config; // 或者您存放配置类的包名

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

/**
 * Jackson Mixin to help deserialize GrantedAuthority interface.
 * Jackson needs to know how to instantiate a concrete class (SimpleGrantedAuthority)
 * when it encounters the GrantedAuthority interface during deserialization.
 *
 * Jackson Mixin，用于帮助反序列化 GrantedAuthority 接口。
 * Jackson 需要知道在反序列化过程中遇到 GrantedAuthority 接口时，如何实例化一个具体的类（SimpleGrantedAuthority）。
 */
public abstract class SimpleGrantedAuthorityMixin implements GrantedAuthority {

    /**
     * Jackson will use this constructor to deserialize SimpleGrantedAuthority.
     * The JSON representation of SimpleGrantedAuthority is typically just the role string.
     * We use @JsonProperty("") because SimpleGrantedAuthority's constructor takes a single string argument
     * which corresponds to the JSON value itself, not a named property.
     *
     * Jackson 将使用此构造函数来反序列化 SimpleGrantedAuthority。
     * SimpleGrantedAuthority 的 JSON 表示通常只是角色字符串。
     * 我们使用 @JsonProperty("") 是因为 SimpleGrantedAuthority 的构造函数接受一个字符串参数，
     * 该参数对应于 JSON 值本身，而不是一个命名的属性。
     */
    @JsonCreator
    public SimpleGrantedAuthorityMixin(@JsonProperty("") String role) {
        // This constructor is only for Jackson's internal use during deserialization.
        // The actual SimpleGrantedAuthority object will be created by Jackson
        // using this constructor signature and the provided role string from JSON.
        //
        // 此构造函数仅供 Jackson 在反序列化过程中内部使用。
        // 实际的 SimpleGrantedAuthority 对象将由 Jackson 使用此构造函数签名
        // 和从 JSON 提供的角色字符串来创建。
    }

    // No need to add other methods from GrantedAuthority;
    // Jackson will delegate method calls to the actual SimpleGrantedAuthority instance
    // that it creates using the @JsonCreator constructor.
    //
    // 无需添加 GrantedAuthority 的其他方法；
    // Jackson 会将方法调用委托给它使用 @JsonCreator 构造函数创建的实际 SimpleGrantedAuthority 实例。
}
