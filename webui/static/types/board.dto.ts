/**
 * Created by gergopados on 2017. 02. 01..
 */
module gol{
    export class BoardDto {
        board: number[][];

        constructor(d?: BoardDto) {
            d = d || <BoardDto>{};

            this.board = d.board;
        }
    }
}
