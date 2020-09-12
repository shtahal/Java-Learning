package demo_ClassorAndImplementAsMemoryVariable;

public class GameDemo {

    public static void main(String[] args) {

        Hero hero = new Hero();
        hero.setName("Arthur");

//        Skill skill = new Skill() {         // 使用匿名类
//            @Override
//            public void method() {
//                System.out.println("cut");
//            }
//        };
//        hero.setSkill(skill);

        hero.setSkill(new Skill() {         // 使用匿名类和匿名对象
            @Override
            public void method() {
                System.out.print("chop");
            }
        });

        hero.method();

    }

}
