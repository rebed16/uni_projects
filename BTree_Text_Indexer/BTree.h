#ifndef BTREE_H_
#define BTREE_H_

#include <string>
#include <vector>
#include <iostream>
#include <fstream>

using namespace std;

class Node {
public:
    vector<string> keys;
    vector<vector<pair<int, int>>> values; // aparitiile cuv, perechi (linie, pozitie)
    vector<Node*> children;
    int t; // Grad minim
    bool leaf;


    Node(int t, bool leaf);
    ~Node();
    void traverse() const;
    Node* search(const string& key);
    void insertNonFull(const string& key, int line, int position);
    void splitChild(size_t i, Node* y);
    void exportCSV(ofstream &outFile) const;

    friend class BTree;
};

class BTree {
public:

    int t;
    Node* root;
    BTree(int t);
    ~BTree();
    void traverse() const;
    Node* search(const string& key);
    void insert(const string& key, int line, int position);
    void exportCSV(ofstream &outFile) const;
};

#endif // BTREE_H_
