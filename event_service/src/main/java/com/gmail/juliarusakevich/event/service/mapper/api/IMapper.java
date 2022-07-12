package com.gmail.juliarusakevich.event.service.mapper.api;

public interface IMapper<F, T> {

    T map(F object);

   // default T map(F fromObject, T toObject) {
   //     return toObject;
   // }
}
