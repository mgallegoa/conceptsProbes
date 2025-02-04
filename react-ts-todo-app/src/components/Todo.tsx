import { todoId, type Todo as TodoType } from "../types";

interface Props extends TodoType {
  onRemoveTodo: ({ id }: todoId) => void;
}

export const Todo: React.FC<Props> = ({
  title,
  id,
  completed,
  onRemoveTodo,
}) => {
  return (
    <div className="view" key={id}>
      <input
        className="toggle"
        type="checkbox"
        checked={completed}
        onChange={() => {}}
      />
      <label>{title}</label>
      <button
        className="destroy"
        onClick={() => {
          onRemoveTodo({ id });
        }}
      />
    </div>
  );
};
