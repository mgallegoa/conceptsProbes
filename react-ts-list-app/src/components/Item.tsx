export const Item = ({
  text,
  handleClick,
}: {
  text: string;
  handleClick: () => void;
}) => {
  return (
    <li>
      <p className="list__text">{text}</p>
      <button
        className="button__delete"
        onClick={handleClick}
        // onClick={() => {
        //   setItems((prevElements) => {
        //     return prevElements.filter(
        //       (currentItem) => currentItem.id !== item.id,
        //     );
        //   });
        // }}
      >
        x
      </button>
    </li>
  );
};
