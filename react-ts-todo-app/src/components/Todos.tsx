import { Todo as TodoType, todoId, type ListOfTodos } from "../types";
import { Todo } from "./Todo";

interface Props {
  todos: ListOfTodos;
  onRemoveTodo: ({ id }: todoId) => void;
  onCompleted: ({ id, completed }: Pick<TodoType, "id" | "completed">) => void;
}

export const Todos: React.FC<Props> = ({
  todos,
  onRemoveTodo,
  onCompleted,
}) => {
  return (
    <ul className="todo-list">
      {todos.map((todo) => (
        <li key={todo.id} className={`${todo.completed ? "completed" : ""}`}>
          <Todo
            key={todo.id}
            title={todo.title}
            id={todo.id}
            completed={todo.completed}
            onRemoveTodo={onRemoveTodo}
            onCompleted={onCompleted}
          />
        </li>
      ))}
    </ul>
  );
};
