package com.game.of.life.dao;

import com.game.of.life.model.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gergopados on 2017. 02. 01..
 */
@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {
}
