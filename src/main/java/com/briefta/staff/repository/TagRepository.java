package com.briefta.staff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.briefta.staff.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	List<Tag> findTagsByCompanyId(long id);
	@Query(value="SELECT * FROM tag where id NOT IN (SELECT tag_id from visit where date(time_in)=curdate() and time_out is null) and status='available' and company_id=?1",nativeQuery = true)
	List<Tag> findTagsNotGivenOut(long id);
	Optional<Tag> findTagByTagIdAndCompanyId(String tagName,long companyId);
}
