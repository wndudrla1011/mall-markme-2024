import React, { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import { getList } from "../../api/itemsApi";
import { Button, Card } from "react-bootstrap";
import { API_SERVER_HOST } from "../../App";
import styles from "../../css/card.module.css";

const host = API_SERVER_HOST;

const initState = {
  dtoList: [],
  pageNumberList: [],
  pageRequestDTO: null,
  prev: false,
  next: false,
  totalCount: 0,
  prevPage: 0,
  nextPage: 0,
  totalPage: 0,
  current: 0,
};

function ListComponent() {
  const { page, size, moveToList } = useCustomMove();

  const [serverData, setServerData] = useState(initState); //api response

  useEffect(() => {
    getList({ page, size }).then((data) => {
      console.log(data);
      setServerData(data); //setting response
    });
  }, [page, size]);

  return (
    <div className={styles.card__container}>
      {serverData.dtoList.map((item) => (
        <div key={item.id} className={styles.card__holder}>
          <Card style={{ width: "18rem" }}>
            <Card.Img
              variant="top"
              src={`${host}/api/items/read/s_${item.uploadFileNames[0]}`}
            />
            <Card.Body className="my-5 space-y-2">
              <Card.Title>{item.name}</Card.Title>
              <Card.Subtitle>{item.writer}</Card.Subtitle>
              <Card.Text>{item.description}</Card.Text>
              <Button variant="primary">Show Details</Button>
            </Card.Body>
          </Card>
        </div>
      ))}
    </div>
  );
}

export default ListComponent;
