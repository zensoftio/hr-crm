import { computed, observable } from "mobx";

class Todo {
    @observable value;
    @observable id;
    @observable complete;

    constructor(value) {
        this.value = value;
        this.complete = false;
    }
}
class TodoStore {
    @observable todos = ['buy milk', 'buy eggs'];
    @observable filter = '';
    @computed get filteredTodos() {
        let matchesFilter = new RegExp(this.filter, "i");
        return this.todos.filter(todo => !this.filter || matchesFilter.test(todo));
    }
    createTodo(value) {
        this.todos.push(new Todo(value));
    }
}


export default new TodoStore;
