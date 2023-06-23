import {Colour} from "./color";

export class GarmentDetails {
  image : Blob;
  size : string;
  colour : Colour;
  discount : boolean;
  discountPercentage : number;
}
