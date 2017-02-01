/**
 * Created by gergopados on 2017. 02. 01..
 */
module gol {
    export class Dimension {
        width: number;
        height: number;

        constructor(d?: Dimension) {
            d = d || <Dimension>{};

            this.width = d.width;
            this.height = d.height;
        }
    }
}