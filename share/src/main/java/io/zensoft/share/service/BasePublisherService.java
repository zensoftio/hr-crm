package io.zensoft.share.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public abstract class BasePublisherService implements PublisherService {
    protected RestTemplate restTemplate;


}
