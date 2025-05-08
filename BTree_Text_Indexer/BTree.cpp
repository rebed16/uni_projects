#include "BTree.h"

// Constructor pentru nod
Node::Node(int t, bool leaf) : t(t), leaf(leaf) {}

Node::~Node() {
    for (Node* child : children) {
        delete child;
    }
    children.clear();
}

// Constructor pentru arbore
BTree::BTree(int t) : t(t), root(nullptr) {}

BTree::~BTree() {
    delete root;
    root = nullptr;
}

// Parcurge si afiseaza arborele
void Node::traverse() const {
    for (size_t i = 0; i < keys.size(); ++i) {
        if (!leaf) {
            children[i]->traverse();
        }
        cout << keys[i] << ": ";
        for (const auto& loc : values[i]) {
            cout << "(Linia " << loc.first << ", Pozitia " << loc.second << ") ";
        }
        cout << endl;
    }
    if (!leaf) {
        children.back()->traverse();
    }
}

void BTree::traverse() const {
    if (root) root->traverse();
}

// Cauta un cuvant in arbore
Node* Node::search(const string& key) {
    size_t i = 0;
    while (i < keys.size() && key > keys[i]) i++;

    if (i < keys.size() && keys[i] == key) return this;

    if (leaf) return nullptr;

    return children[i]->search(key);
}

Node* BTree::search(const string& key) {
    return root ? root->search(key) : nullptr;
}

// Insereaza o cheie si locatia ei
void BTree::insert(const string& key, int line, int position) {
    if (!root) {
        root = new Node(t, true);
        root->keys.push_back(key);
        root->values.push_back({{line, position}});
    } else {
        if (root->keys.size() == 2 * t - 1) {
            Node* newRoot = new Node(t, false);
            newRoot->children.push_back(root);
            newRoot->splitChild(0, root);

            size_t i = (newRoot->keys[0] < key) ? 1 : 0;
            newRoot->children[i]->insertNonFull(key, line, position);

            root = newRoot;
        } else {
            root->insertNonFull(key, line, position);
        }
    }
}

void Node::insertNonFull(const string& key, int line, int position) {
    size_t i = keys.size();

    if (leaf) {
        while (i > 0 && keys[i - 1] > key) i--;

        if (i > 0 && keys[i - 1] == key) {
            values[i - 1].emplace_back(line, position);
        } else {
            keys.insert(keys.begin() + i, key);
            values.insert(values.begin() + i, {{line, position}});
        }
    } else {
        while (i > 0 && keys[i - 1] > key) i--;

        if (children[i]->keys.size() == 2 * t - 1) {
            splitChild(i, children[i]);

            if (keys[i] < key) i++;
        }
        children[i]->insertNonFull(key, line, position);
    }
}

void Node::splitChild(size_t i, Node* y) {
    Node* z = new Node(y->t, y->leaf);
    z->keys.assign(y->keys.begin() + t, y->keys.end());
    z->values.assign(y->values.begin() + t, y->values.end());

    y->keys.resize(t - 1);
    y->values.resize(t - 1);

    if (!y->leaf) {
        z->children.assign(y->children.begin() + t, y->children.end());
        y->children.resize(t);
    }

    children.insert(children.begin() + i + 1, z);
    keys.insert(keys.begin() + i, y->keys[t - 1]);
    values.insert(values.begin() + i, y->values[t - 1]);
}

// Exporta datele in fisier CSV
void Node::exportCSV(ofstream &outFile) const {
    for (size_t i = 0; i < keys.size(); i++) {
        outFile << keys[i] << ",\"";
        for (size_t j = 0; j < values[i].size(); j++) {
            outFile << "(Linia " << values[i][j].first << ", Pozitia " << values[i][j].second << ")";
            if (j < values[i].size() - 1) outFile << ", ";
        }
        outFile << "\"\n";

        if (!leaf) {
            children[i]->exportCSV(outFile);
        }
    }
    if (!leaf) {
        children.back()->exportCSV(outFile);
    }
}

void BTree::exportCSV(ofstream &outFile) const {
    if (root) root->exportCSV(outFile);
}
