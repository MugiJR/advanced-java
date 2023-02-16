package lamdas;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Account {
    private Long id;
    private String name;
    private Long parentId;
    private String parentName;

    public Account(Long id, String name, Long parentId, String parentName) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}

class AccountFinance {
    private Long id;
    private String name;
    private Long parentId;
    private String parentName;

    public AccountFinance(Long id, String name, Long parentId, String parentName) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "AccountFinance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}



public class CompareObjects {

    public static void main(String[] args) {
        List<Account> accountLists = List.of(
                new Account(1L,"Account 1", 101L, "Parent Account 1"),
                new Account(2L,"Account 2", 102L, "Parent Account 2"),
                new Account(3L,"Account 3", 103L, "Parent Account 3"),
                new Account(4L,"Account 4", 104L, "Parent Account 4")
        );

        List<AccountFinance> accountsToAdd = List.of(
                new AccountFinance(1L,"Account 1", 101L, "Parent Account 1"),
                new AccountFinance(2L,"Updated New Account 2", 102L, "Parent Account 2")
        );

        Predicate<AccountFinance> isSameAccountExists = (acc) -> {
            var sameAccounts = accountLists.stream()
                    .filter(x->
                            x.getId() == acc.getId() &&
                                    x.getName() == acc.getName() &&
                                    x.getParentId() == acc.getParentId() &&
                                    x.getParentName() == acc.getParentName()
                    ).collect(Collectors.toList()).size();
            return sameAccounts > 0;
        };

        List<AccountFinance> newAccountToAdd = accountsToAdd.stream().filter(x->!isSameAccountExists.test(x)).collect(Collectors.toList());

        newAccountToAdd.forEach(System.out::println);
    }

}
