/**
 * Created by gergopados on 2017. 02. 01..
 */
module gol {

    export interface GameOfLifeControllerScope extends ng.IScope {
        dimension: Dimension;
        board: number[][];
        toggle: (row: number, col: number) => void;
        init: () => void;
        next: () => void;

        autoPlay: () => void;
        isAutoPlayInProgress: boolean;
    }

    export class GameOfLifeController {

        private $inject = ['$scope', 'GameOfLifeService', '$interval'];

        private stopInterval: ng.IPromise<any>;

        constructor(public $scope: GameOfLifeControllerScope,
                    GameOfLifeService: IGameOfLifeService,
                    public $interval: ng.IIntervalService) {
            $scope.dimension = new Dimension({
                width: 10,
                height: 10
            });

            $scope.toggle = (row, col) => {
                var current = $scope.board[row][col];
                $scope.board[row][col] = current == 0 ? 1 : 0;
            };

            $scope.init = () => {
                this.init();
            };

            $scope.next = () => {
                GameOfLifeService.getNextGeneration($scope.board).then((nextGen)=> {
                    $scope.board = nextGen.data.board;

                    $scope.dimension.width = $scope.board.length;
                    $scope.dimension.height = $scope.board[0].length;
                });
            }

            $scope.autoPlay = ()=> {
                if (!$scope.isAutoPlayInProgress) {
                    this.stopInterval = $interval($scope.next, 1000);
                }
                else {
                    $interval.cancel(this.stopInterval);
                }

                $scope.isAutoPlayInProgress = !$scope.isAutoPlayInProgress;

            };

            this.init();
        }

        private init() {
            this.$scope.board = [];

            for (var h = 0; h < this.$scope.dimension.height; h++) {
                var row = [];
                for (var w = 0; w < this.$scope.dimension.width; w++) {
                    row.push(0);
                }
                this.$scope.board.push(row);
            }
        }

    }


    angular.module('gol').controller('GameOfLifeController', GameOfLifeController);
}