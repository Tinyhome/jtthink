package com.db.Mapper;

import org.springframework.stereotype.Service;

/**
 * Created by Tinyhome on 16/9/4.
 */


@Service
public interface ClientMapper {
     int getClient(ClientEntity clientEntity);
}
