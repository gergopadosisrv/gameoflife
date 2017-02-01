/**
 * Created by gergopados on 2017. 02. 01..
 */
module gol {
    export interface IGameOfLifeService {
        getNextGeneration: (board: BoardDto) => ng.IHttpPromise<BoardDto>;
        getAllPattern: () => ng.IHttpPromise<BoardDto[]>;
        savePattern: (board: BoardDto) => ng.IHttpPromise<number>;
    }

    export class GameOfLifeService implements IGameOfLifeService {
        private $http: ng.IHttpService;

        constructor(public _$http: ng.IHttpService) {
            this.$http = _$http;
        }

        getNextGeneration(board: BoardDto) {
            return this.$http.post("api/getNextGeneration", board);
        }

        getAllPattern() {
            return this.$http.get("api/getAllPattern");
        }

        savePattern(board: BoardDto) {
            return this.$http.post("api/savePattern", board);
        }
    }

    angular.module('gol').service('GameOfLifeService', ['$http', GameOfLifeService]);
}