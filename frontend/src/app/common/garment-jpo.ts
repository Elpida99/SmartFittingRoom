import {Material} from "./material";

export class Garment {
  name: string;
  code: string;
  description: string;
  price: number;
  categoryName: string;
  categoryCode: string;
  colorName: string;
  colorCode: string;
  discountPercentage: number;
  targetConsumer: string;
  material: Material[];
  isActive: boolean;

}
