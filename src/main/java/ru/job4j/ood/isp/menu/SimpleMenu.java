package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    /*  Если parentName равен null, то childName добавляете в rootElements и он становится корневым элементом.
     Если parentName не равен null,
     то проверить, что parentName существует и добавить ему в потомки childName  */
    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (parentName == null) {
            actionDelegate.delegate();
            return rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        }
    /*  это копия для наглядности конструктора:
  private static class SimpleMenuItem implements MenuItem {
        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }
*/
        boolean flag = false;
        for (MenuItem menuItem : rootElements) {
            if (parentName.equals(menuItem.getName())) {
                MenuItem children = new SimpleMenuItem(childName, actionDelegate);
                menuItem.addChildren(children);

                actionDelegate.delegate();
                flag = true;
                break;
            }
        }
        if (!flag) {
            MenuItem menuItem = new SimpleMenuItem(parentName, actionDelegate);
            actionDelegate.delegate();
            return rootElements.add(menuItem);
        }
        return flag;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return Optional.of(new MenuItemInfo(
                findItem(itemName).get().menuItem,
                findItem(itemName).get().number));
    }

    /* DFSIterator  отдает ItemInfo, а итератор, который надо реализовать,
     должен отдавать MenuItemInfo Выполните его на основе DFSIterator.  */
    @Override
    public Iterator<MenuItemInfo> iterator() {
        class MenuItemInfoIterator implements Iterator<MenuItemInfo> {
            private Iterator<ItemInfo> dfsIterator;

            MenuItemInfoIterator() {
                this.dfsIterator = new DFSIterator();
            }

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        }

        return new MenuItemInfoIterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> result = Optional.empty();
        DFSIterator iterator = new DFSIterator();
        while (iterator().hasNext()) {
            ItemInfo itemInfoTemp = iterator.next();
            if (name.equals(itemInfoTemp.menuItem.getName())) {
                return Optional.of(itemInfoTemp);
            }
        }
        return result;
    }

    private static class SimpleMenuItem implements MenuItem {
        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        public SimpleMenuItem(String name, MenuItem children) {
            this.name = name;
            this.children.add(children);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }

        @Override
        public ActionDelegate setActionDelegate(ActionDelegate actionDelegate) {
            this.actionDelegate = actionDelegate;
            return this.actionDelegate;
        }

        @Override
        public List<MenuItem> addChildren(MenuItem child) {
            children.add(child);
            return children;
        }

        @Override
        public String toString() {
            return "SimpleMenuItem{" +
                    "name='" + name + '\'' +
                    ", children=" + children +
                    ", actionDelegate=" + actionDelegate +
                    '}' + System.lineSeparator();
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {
        Deque<MenuItem> stack = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious(); ) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

        @Override
        public String toString() {
            return number
                    + "menuItem=" + menuItem;
        }
    }

    @Override
    public String toString() {
        return "SimpleMenu{" +
                "rootElements=" + rootElements +
                '}';
    }
}
