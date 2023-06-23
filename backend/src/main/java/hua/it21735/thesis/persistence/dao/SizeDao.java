package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Size;
import hua.it21735.thesis.persistence.model.SizeEnum;
import hua.it21735.thesis.persistence.model.TargetConsumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SizeDao extends JpaRepository<Size, Long> {

    Optional<Size> findBySizeAndGender(SizeEnum size, TargetConsumer gender);

    List<Size> findBySize(SizeEnum size);

    List<Size> findByGender(TargetConsumer gender);
}
