package net.mistiksdevs.literalura.network.converter;

public interface IDataConverter {
    <T> T convert(String data, Class<T> clazz);
}
