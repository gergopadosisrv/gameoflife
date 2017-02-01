/**
 * Created by gergopados on 2017. 02. 01..
 */
module gol {
    export class BoardDto {
        id: number;
        name: string;
        board: number[][];

        constructor(d?: BoardDto) {
            d = d || <BoardDto>{};

            this.board = d.board || [];
            this.id = d.id || 0;
            this.name = d.name || '';
        }
    }
}
