export const Item = ({
  text,
  handleClick,
}: {
  text: string;
  handleClick: () => void;
}) => {
  return (
    <li>
      {text}
      <button
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
