package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.domain.requests.GoalFilterRequest;
import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.user.domain.Credential;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalPostgresRepository extends JpaRepository<BaseGoal, UUID> {
    Optional<BaseGoal> findGoalByIdAndOwner(UUID id, Credential owner);

    @Query(value = """

    WITH RECURSIVE start_page AS (
        SELECT g.id, g.owner, g.level
        FROM goal g
        JOIN credential c ON c.id = g.owner
        WHERE c.email = :email
          AND g.level = :#{#filter.startLevel}
        ORDER BY g.updated_at ASC, g.id ASC
        LIMIT :#{#filter.width} OFFSET :#{#filter.startOffset}
    ),
    goal_tree AS (
        SELECT s.id, s.owner, s.level
        FROM start_page s
    
        UNION ALL
    
        SELECT child.id, child.owner, child.level
        FROM goal_tree parent
        CROSS JOIN LATERAL (
            SELECT g.id, g.owner, g.level
            FROM goal g
            WHERE g.parent_goal = parent.id
              AND g.owner = parent.owner
              AND parent.level < :#{#filter.startLevel + #filter.depth - 1}
            ORDER BY g.updated_at ASC, g.id ASC
            LIMIT :#{#filter.width}
        ) child
    )
    SELECT g.*
    FROM goal g
    JOIN goal_tree t ON t.id = g.id
    ORDER BY g.updated_at ASC, g.id ASC
    """, nativeQuery = true)
    List<BaseGoal> findGoalHierarchyByEmail
            (@Param("email") String email, @Param("filter") GoalFilterRequest goalFilterRequest);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query(value = """
    WITH RECURSIVE goal_tree(id) AS (
        SELECT g.id
        FROM goal g
        WHERE g.id = :goalId
          AND g.owner = :ownerId

        UNION

        SELECT child.id
        FROM goal child
        JOIN goal_tree parent ON child.parent_goal = parent.id
        WHERE child.owner = :ownerId
    )
    DELETE FROM goal g
    USING goal_tree tree
    WHERE g.id = tree.id
    """, nativeQuery = true)
    int deleteGoalTree(
            @Param("goalId") UUID goalId,
            @Param("ownerId") UUID ownerId
    );
}
