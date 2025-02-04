import { useState } from "react";
import { Todos } from "./components/Todos";
import { FilterValue, Todo as TodoType, type todoId } from "./types";
import { TODO_FILTERS } from "./const";
import { Footer } from "./components/Footer";

const mocksTodo = [
  {
    id: "1",
    title: "todo 1",
    completed: true,
  },
  {
    id: "2",
    title: "todo 2",
    completed: false,
  },
  {
    id: "3",
    title: "todo 3",
    completed: false,
  },
];
const App = () => {
  const [todos, setTodos] = useState(mocksTodo);
  const [filterSelected, setFilterSelected] = useState<FilterValue>(
    TODO_FILTERS.ALL,
  );

  const handleRemove = ({ id }: todoId) => {
    const newTodos = todos.filter((todo) => todo.id !== id);
    setTodos(newTodos);
  };

  const handleCompleted = ({
    id,
    completed,
  }: Pick<TodoType, "id" | "completed">) => {
    const newTodos = todos.map((todo) => {
      if (todo.id === id) {
        return { ...todo, completed };
      }
      return todo;
    });

    setTodos(newTodos);
  };

  const handleFilterChange = (filter: FilterValue): void => {
    setFilterSelected(filter);
  };

  const filterTodos = todos.filter((todo) => {
    if (filterSelected === TODO_FILTERS.ACTIVE) return !todo.completed;
    if (filterSelected === TODO_FILTERS.COMPLETED) return todo.completed;
    return todo;
  });

  const activeCount = todos.filter((todo) => !todo.completed).length;
  const completedCount = todos.filter((todo) => todo.completed).length;

  return (
    <>
      <div className="todoapp">
        <Todos
          onRemoveTodo={handleRemove}
          onCompleted={handleCompleted}
          todos={filterTodos}
        />
        <Footer
          activeCount={activeCount}
          completedCount={completedCount}
          filterSelected={filterSelected}
          handlerFilterChange={handleFilterChange}
          onClearCompleted={() => {}}
        />
      </div>
    </>
  );
};

export default App;
