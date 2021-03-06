package me.yushust.inject.identity.token;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

class ParameterizedToken implements ParameterizedType {

    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;

    ParameterizedToken(ParameterizedType prototype) {
        this(prototype.getOwnerType(), prototype.getRawType(), prototype.getActualTypeArguments());
    }

    ParameterizedToken(Type ownerType, Type rawType, Type... typeArguments) {

        this.ownerType = ownerType == null ? null : Types.wrap(ownerType);
        this.rawType = Types.wrap(rawType);
        this.typeArguments = typeArguments.clone();

        for (int t = 0, length = this.typeArguments.length; t < length; t++) {
            this.typeArguments[t] = Types.wrap(this.typeArguments[t]);
        }

    }

    @Override
    public Type[] getActualTypeArguments() {
        return typeArguments.clone();
    }

    @Override
    public Type getRawType() {
        return rawType;
    }

    @Override
    public Type getOwnerType() {
        return ownerType;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ParameterizedType
                && Types.typeEquals(this, (ParameterizedType) other);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(typeArguments)
                ^ rawType.hashCode()
                ^ (ownerType == null ? 0 : ownerType.hashCode());
    }

    @Override
    public String toString() {
        int length = typeArguments.length;
        if (length == 0) {
            return Types.asString(rawType);
        }

        StringBuilder stringBuilder = new StringBuilder(30 * (length + 1));
        stringBuilder.append(Types.asString(rawType)).append("<").append(Types.asString(typeArguments[0]));
        for (int i = 1; i < length; i++) {
            stringBuilder.append(", ").append(Types.asString(typeArguments[i]));
        }
        return stringBuilder.append(">").toString();
    }

}
