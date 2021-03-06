package me.yushust.inject.identity.token.resolve;

import me.yushust.inject.identity.token.ContextualTypes;
import me.yushust.inject.identity.token.Token;
import me.yushust.inject.identity.token.Types;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

import static me.yushust.inject.internal.Preconditions.checkArgument;

public class GenericArrayResolver implements TypeResolver {

    @Override
    public Type resolveType(Token<?> context, Type type) {

        checkArgument(type instanceof GenericArrayType, "Type isn't an instance of GenericArrayType!");

        Type componentType = ((GenericArrayType) type).getGenericComponentType();
        Type resolvedComponentType = ContextualTypes.resolveContextually(context, componentType);

        if (componentType == resolvedComponentType) {
            return type;
        }

        return Types.genericArrayTypeOf(resolvedComponentType);

    }

}
