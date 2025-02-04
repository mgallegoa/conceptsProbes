import { useState } from "react";
import { Todos } from "./components/Todos";
import { Todo as TodoType, type todoId } from "./types";

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
  return (
    <>
      <div className="todoapp">
        <Todos
          onRemoveTodo={handleRemove}
          onCompleted={handleCompleted}
          todos={todos}
        />
      </div>
    </>
  );
};

export default App;
