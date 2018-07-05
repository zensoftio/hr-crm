import { promises } from "fs";

class PositionService {
    create(data) {
        return Promise.resolve(data)
    }
}

const positionService = new PositionService();

export default positionService ;