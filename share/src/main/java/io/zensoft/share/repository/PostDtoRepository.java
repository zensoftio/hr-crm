package io.zensoft.share.repository;

import io.zensoft.share.model.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDtoRepository extends JpaRepository<PostDto, Long> {

}
