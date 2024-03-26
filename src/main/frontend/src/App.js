import { RouterProvider } from "react-router-dom";
import root from "./router/root";

export const API_SERVER_HOST = "http://localhost:8080";

function App() {
  return <RouterProvider router={root} />;
}

export default App;
