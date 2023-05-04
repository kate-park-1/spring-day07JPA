import domain.Member;
import domain.RoleType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Member_Team_Create {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("AI-PK : 생성 트랜잭션 시작");

    try {
      tx.begin();
      Team team = new Team();
      team.setName("teamA");
      em.persist(team);

      Member member = new Member();
      member.setUsername("basicMember1");
      member.setRoleType(RoleType.BASIC);
      member.setTeamId(team.getId());
      em.persist(member);

      Member memberA=new Member();
      memberA.setUsername("managerMember1");
      memberA.setRoleType(RoleType.MANAGER);
      memberA.setTeamId(team.getId());
      em.persist(memberA);

      System.out.println("영속 상태 --------");
      Member member1 = em.find(Member.class, member.getId());
      System.out.println("member == member1 ? " + member.equals(member1));
      System.out.println("커밋 전 --------");
      tx.commit();
      System.out.println("커밋 후 --------");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }
}
