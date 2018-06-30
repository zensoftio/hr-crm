package io.zensoft.share.repository;

import io.zensoft.share.model.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostResponseRepository extends JpaRepository<PostResponse, Long> {

}