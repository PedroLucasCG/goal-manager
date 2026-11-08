package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.user.domain.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalPostgresRepository extends JpaRepository<Goal, UUID> {
    Optional<Goal> findGoalByIdAndOwner(UUID id, Credential owner);

    @Query(value = """
    WITH RECURSIVE goal_tree AS (
        SELECT g.id, g.owner
        FROM goal g
        JOIN credential c ON c.id = g.owner
        WHERE c.email = :email
          AND g.parent_goal IS NULL

        UNION ALL

        SELECT child.id, child.owner
        FROM goal child
        JOIN goal_tree parent ON child.parent_goal = parent.id
                             AND child.owner = parent.owner
    )
    SELECT g.*
    FROM goal g
    JOIN goal_tree tree ON tree.id = g.id
    """, nativeQuery = true)
    List<Goal> findGoalHierarchyByEmail(@Param("email") String email);
}
