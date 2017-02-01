/**
 * Created by gergopados on 2017. 02. 01..
 */
module gol {
    export interface IGameOfLifeService {
        getNextGeneration: (board: number[][]) => ng.IHttpPromise<BoardDto>;
    }

    export class GameOfLifeService implements IGameOfLifeService {
        private $http: ng.IHttpService;

        constructor(public _$http: ng.IHttpService) {
            this.$http = _$http;
        }

        getNextGeneration(board: number[][]) {
            var a = new BoardDto();
            return this.$http.post("api/getNextGeneration", new BoardDto({board: board}));
        }
    }

    angular.module('gol').service('GameOfLifeService', ['$http', GameOfLifeService]);
}