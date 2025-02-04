import { type Todo as TodoType } from "../types";

type Props = TodoType;

export const Todo: React.FC<Props> = ({ title, id, completed }) => {
  return (
    <div className="view" key={id}>
      <input
        className="toggle"
        type="checkbox"
        checked={completed}
        onChange={() => {}}
      />
      <label>{title}</label>
      <button className="destroy" onClick={() => {}} />
    </div>
  );
};
